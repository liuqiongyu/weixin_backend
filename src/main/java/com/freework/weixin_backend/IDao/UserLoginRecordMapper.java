package com.freework.weixin_backend.IDao;

import com.freework.weixin_backend.domain.UserLoginRecord;

public interface UserLoginRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserLoginRecord record);

    int insertSelective(UserLoginRecord record);

    UserLoginRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserLoginRecord record);

    int updateByPrimaryKey(UserLoginRecord record);
}