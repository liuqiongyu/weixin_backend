package com.freework.weixin_backend.IDao;

import com.freework.weixin_backend.domain.WxUserInfo;

import java.util.List;
import java.util.Map;

public interface WxUserInfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(WxUserInfo record);

    int insertSelective(WxUserInfo record);

    WxUserInfo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(WxUserInfo record);

    int updateByPrimaryKey(WxUserInfo record);

    /**
     * 根据内部的应用标识和OPENID查询微信用户信息
     *
     * @param paramMap["appId"] 内部的微信小程序应用标识，配置在微信小程序内部
     * @param paramMap["openId"] 微信登陆后，从微信用户信息中取出的openId参数
     * @return WxUserInfo
     *      NULL  ：记录不存在
     *      NOT NULL： 对应的微信用户记录，里面内部的userId
     */
    WxUserInfo selectByWxOpenId(Map<String, Object> paramMap);
}