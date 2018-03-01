package com.freework.weixin_backend.IDao;

import com.freework.weixin_backend.domain.Content;

import java.util.List;
import java.util.Map;

public interface ContentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Content record);

    int insertSelective(Content record);

    Content selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);

    /**
     * 查询内容记录
     *
     * @param paramMap["appId"] 内部的微信小程序应用标识，配置在微信小程序内部
     * @param paramMap["limitNum"] 返回的记录数，对应 sql 的limit参数，List<Content>.length <= 该值
     * @param paramMap["offsetNum"] ，对应sql 的offset参数
     * @return List<Content>
     *      NOT NULL： 对应的内容记录
     */
    List<Content> selectContent(Map<String, Object> paramMap);
}