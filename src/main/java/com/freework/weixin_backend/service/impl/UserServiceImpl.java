package com.freework.weixin_backend.service.impl;

import com.freework.weixin_backend.IDao.AppInfoMapper;
import com.freework.weixin_backend.IDao.UserLoginRecordMapper;
import com.freework.weixin_backend.IDao.WxUserInfoMapper;
import com.freework.weixin_backend.domain.AppInfo;
import com.freework.weixin_backend.domain.UserLoginRecord;
import com.freework.weixin_backend.domain.WxUserInfo;
import com.freework.weixin_backend.service.UserService;
import com.freework.weixin_backend.utils.AesCbcUtil;
import com.freework.weixin_backend.utils.ErrorCode;
import com.freework.weixin_backend.utils.HttpRequest;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 对微信小程序提供的跟用户相关接口的服务实现类
 */

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    AppInfoMapper appInfoMapper;

    @Autowired
    WxUserInfoMapper wxUserInfoMapper;

    @Autowired
    UserLoginRecordMapper userLoginRecordMapper;

    /**
     * 微信小程序登陆后台处理服务
     * 1.根据内部的应用标识查询微信APPID和APPSECRET；
     * 2.根据登陆凭证从微信后台查询SESSIONKEY，并且解密微信登录用户信息；
     * 3.根据OPENID和内部的应用ID判断微信用户表中是否有对应的记录，如果没有，增加一条记录，如果有则更新该记录；
     * 4.记录微信用户的登陆操作到登陆日志表；
     * 5.返回微信登陆用户信息，及本地用户的ID，做TOKEN用；
     *
     * @param appId 内部的微信小程序应用标识，配置在微信小程序内部
     * @param encryptedData 微信小程序登陆微信后，获取的用户信息密文
     * @param iv AES加密算法的偏移量参数
     * @param code 微信小程序的登陆凭证
     * @param response 响应数据 response["data"] = 响应报文的JSON串
     * @return ErrorCode 处理结果码，成功 OR 其他错误
     */
    @Transactional
    public ErrorCode userLogin(String appId, String encryptedData, String iv, String code, Map response){
        AppInfo appInfo = appInfoMapper.getAppInfoById( appId );
        if( null == appInfo ){
            logger.error("查询[appid={}]的应用信息失败",appId);
            return ErrorCode.ERR_DATABASE;
        }

        String result = queryRemoteWxUserInfo( appInfo,encryptedData,iv,code );
        if ( null == result || result.length() <= 1 ) {
            logger.error("从微信后台查询用户信息失败");
            return ErrorCode.ERR_DECODE;
        }

        JSONObject userInfoJSON = JSONObject.fromObject(result);
        Map userInfo = new HashMap();
        String openId = (String)userInfoJSON.get("openId");

        String nickName =  (String)userInfoJSON.get("nickName");
        userInfo.put("nickName",nickName);

        String gender = userInfoJSON.get("gender").toString();
        userInfo.put("gender",gender);

        String city = (String)userInfoJSON.get("city");
        userInfo.put("city", city);

        String province = (String)userInfoJSON.get("province");
        userInfo.put("province", province);

        String country = (String)userInfoJSON.get("country");
        userInfo.put("country", country);

        String avatarUrl = (String)userInfoJSON.get("avatarUrl");
        userInfo.put("avatarUrl", avatarUrl);

        String unionId = (String)userInfoJSON.get("unionId");
        if ( null != unionId) {
            userInfo.put("unionId", userInfoJSON.get("unionId"));
        }

        //根据appId和openId查询微信用户表中是否有对应的记录存在
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("appId",appId);
        paramMap.put("openId",openId);
        int recordType = 0;
        WxUserInfo wxUserInfo = wxUserInfoMapper.selectByWxOpenId(paramMap);

        //如果微信用户表中没有对应的记录，则创建一条新记录
        if ( null == wxUserInfo ){
            wxUserInfo = new WxUserInfo();
            String userId = UUID.randomUUID().toString();
            userId = userId.replace("-","");
            wxUserInfo.setUserId(userId);
            wxUserInfo.setAvatarUrl(avatarUrl);
            wxUserInfo.setCity(city);
            wxUserInfo.setCountry(country);
            wxUserInfo.setProvince(province);
            wxUserInfo.setCreateDate(new Date());
            wxUserInfo.setGender(gender);
            wxUserInfo.setModifyDate(new Date());
            wxUserInfo.setInnerAppid(appId);
            wxUserInfo.setOpenId(openId);
            wxUserInfo.setNickName(nickName);
            wxUserInfo.setUnionId(unionId);
        }else{ //否则更新微信用户的部分信息，特别是ModifyDate
            recordType = 1;
            wxUserInfo.setModifyDate(new Date());
            wxUserInfo.setNickName( nickName );
            wxUserInfo.setGender( gender );
            wxUserInfo.setProvince( province );
            wxUserInfo.setCountry( country );
            wxUserInfo.setCity( city );
            wxUserInfo.setAvatarUrl( avatarUrl );
        }
        recordUserLogin(wxUserInfo,recordType);
        userInfo.put("token",wxUserInfo.getUserId());

        response.put("data", userInfo);
        return ErrorCode.SUCCEED;
    }

    //新增或更新用户数据，记录用户登陆日志，这两个操作放在一个事物里面完成
    @Transactional
    public void recordUserLogin(WxUserInfo wxUserInfo,int recordType){
        //更新或者新增微信用户数据
        if( 0 == recordType ){
            wxUserInfoMapper.insert(wxUserInfo);
        }else{
            wxUserInfoMapper.updateByPrimaryKey(wxUserInfo);
        }

        //记录用户登陆日志
        UserLoginRecord record = new UserLoginRecord();
        record.setUserId(wxUserInfo.getUserId());
        record.setCreateDate(wxUserInfo.getModifyDate());
        record.setInnerAppid(wxUserInfo.getInnerAppid());
        userLoginRecordMapper.insert(record);
    }

    //从微信后台查询用户信息
    private String queryRemoteWxUserInfo(AppInfo appInfo,String encryptedData, String iv, String code){
        try {
            //小程序唯一标识   (在微信小程序管理后台获取)
            String wxspAppid = appInfo.getWxAppid();
            //小程序的 app secret (在微信小程序管理后台获取)
            String wxspSecret = appInfo.getWxAppsecret();
            //授权（必填）
            String grant_type = "authorization_code";

            //////////////// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
            //请求参数
            String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
            //发送请求
            String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);

            //解析相应内容（转换成json对象）
            JSONObject json = JSONObject.fromObject(sr);
            //获取会话密钥（session_key）
            String session_key = json.get("session_key").toString();
            //用户的唯一标识（openid）
            String openid = (String) json.get("openid");
            //////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
            String result = AesCbcUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
            return  result;
        } catch (Exception e) {
            logger.error("解密微信用户信息失败",e);
            return null;
        }
    }
}
