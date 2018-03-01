package com.freework.weixin_backend.service;

import com.freework.weixin_backend.utils.ErrorCode;

import java.util.Map;

public interface ContentService {

    /**
     * 微信小程序打开后首页显示内容列表处理服务
     *
     * @param appId 内部的微信小程序应用标识，配置在微信小程序内部
     * @param recordNum 查一页前端要求返回的记录数
     * @param pageIndex 查询第几页的数据
     * @param response 响应数据 response["data"] = 响应报文的JSON串
     * @return ErrorCode 处理结果码，成功 OR 其他错误
     */
    public ErrorCode queryContentList(String appId, int recordNum, int pageIndex, Map response);
}
