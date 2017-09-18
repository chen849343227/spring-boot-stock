package com.chen.account.dao;

import com.chen.account.entity.User;

/**
 * author long
 * <p>
 * date 17-9-7
 * <p>
 * desc
 */
public interface UserMapperExtends extends  UserMapper{
    User selectByPhone(String phone);

    User selectByUsername(String username);

}
