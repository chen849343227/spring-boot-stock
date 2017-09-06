package com.chen.account.service;

import com.chen.common.http.entity.Response;

/**
 * author long
 * <p>
 * date 17-9-4
 * <p>
 * desc
 */
public interface IAccountService {
    Response getVerifyCode(String phone);

    Response register(String phone, String pwd, String randomStr);

    Response login(String username, String pwd);
}
