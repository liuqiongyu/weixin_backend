package com.freework.weixin_backend.IDao;

import com.freework.weixin_backend.domain.AppInfo;
import org.apache.ibatis.annotations.Mapper;


public interface AppInfoMapper {
    int insert(AppInfo record);

    int insertSelective(AppInfo record);

    /**
     * 根据内部的应用标识从应用表中查询对应的微信APPID和APPSECRET
     *
     * @param appId 内部的微信小程序应用标识，配置在微信小程序内部
     * @return AppInfo
     *      NULL  ：记录不存在
     *      NOT NULL： 对应的应用记录，里面有微信APPID和APPSECRET
     */
    AppInfo getAppInfoById(String appId);
}