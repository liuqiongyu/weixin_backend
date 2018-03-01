package com.freework.weixin_backend.service;

import com.freework.weixin_backend.utils.ErrorCode;

import java.util.Map;

/**
 * Created by liuqiongyu
 */

public interface UserService {

    /**
     * 微信小程序登陆后台处理服务
     *
     * @param appId 内部的微信小程序应用标识，配置在微信小程序内部
     * @param encryptedData 微信小程序登陆微信后，获取的用户信息密文
     * @param iv AES加密算法的偏移量参数
     * @param code 微信小程序的登陆凭证
     * @param response 响应数据 response["data"] = 响应报文的JSON串
     * @return ErrorCode 处理结果码，成功 OR 其他错误
     */
    public ErrorCode userLogin(String appId, String encryptedData, String iv, String code, Map response);
}
