package com.chen.account.dao;

import com.chen.account.entity.User;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    User selectByPhone(String userName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}