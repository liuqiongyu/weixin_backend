package com.freework.weixin_backend.service.impl;


import com.freework.weixin_backend.service.ContentService;
import com.freework.weixin_backend.utils.ErrorCode;
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
 * 对微信小程序提供的跟内容相关接口的服务实现类
 */

@Service
public class ContentServiceImpl implements ContentService {
    private static final Logger logger = LoggerFactory.getLogger(ContentServiceImpl.class);

    /**
     * 微信小程序打开后首页显示内容列表处理服务
     *
     * @param appId 内部的微信小程序应用标识，配置在微信小程序内部
     * @param recordNum 查一页前端要求返回的记录数
     * @param pageIndex 查询第几页的数据
     * @param response 响应数据 response["data"] = 响应报文的JSON串
     * @return ErrorCode 处理结果码，成功 OR 其他错误
     */
    @Override
    public ErrorCode queryContentList(String appId, int recordNum, int pageIndex, Map response) {

        //TODO:待实现
        return ErrorCode.SUCCEED;
    }


}
