package com.freework.weixin_backend.controller;

import com.freework.weixin_backend.service.UserService;
import com.freework.weixin_backend.utils.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.LoggerFactory;

/**
 * 对微信小程序提供的跟用户相关接口的接入类
 */

@RestController
public class UserController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/weixin_backend/user/userLogin", method = RequestMethod.POST)
    public Map userLogin(String appId, String encryptedData, String iv, String code) {
        logger.info("微信用户登陆:[appid={},encryptedData={},iv={},code={}]...",appId,encryptedData,iv,code);
        Map response = new HashMap();
        //登录凭证不能为空
        if (code == null || code.length() == 0) {
            response.put("code", ErrorCode.ERR_PARAMTER.getErrorCode());
            response.put("msg", ErrorCode.ERR_PARAMTER.getErrorDesc());
            return response;
        }
        try {
            ErrorCode retCode = userService.userLogin(appId, encryptedData, iv, code, response);
            logger.info("userService.userLogin:" + retCode.toString());
            response.put("code", retCode.getErrorCode());
            response.put("msg",retCode.getErrorDesc());
        }catch (Exception e){
            logger.error("微信用户登陆错误",e);
            response.put("code", ErrorCode.ERR_UNKNOWN.getErrorCode());
            response.put("msg",ErrorCode.ERR_UNKNOWN.getErrorDesc());
        }
        return response;
    }
}