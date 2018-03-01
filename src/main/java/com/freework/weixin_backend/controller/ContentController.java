package com.freework.weixin_backend.controller;

import com.freework.weixin_backend.utils.ErrorCode;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * 对微信小程序提供的跟内容相关接口的接入类
 */

@RestController
public class ContentController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(ContentController.class);

    @ResponseBody
    @RequestMapping(value = "/weixin_backend/content/list", method = RequestMethod.GET)
    public Map queryContentList(String appId,int recordNum, int pageIndex) {
        Map response = new HashMap();
        response.put("code", ErrorCode.ERR_UNKNOWN.getErrorCode());
        response.put("msg", ErrorCode.ERR_UNKNOWN.getErrorDesc());
        //TODO:该功能待实现
        return response;
    }

    @ResponseBody
    @RequestMapping(value = "/weixin_backend/content/detail", method = RequestMethod.GET)
    public Map queryContentDetail(String token,String contentId) {
        Map response = new HashMap();
        response.put("code", ErrorCode.ERR_UNKNOWN.getErrorCode());
        response.put("msg", ErrorCode.ERR_UNKNOWN.getErrorDesc());
        //TODO:该功能待实现
        return response;
    }
}