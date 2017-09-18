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

    Response register(String phoneNumber,String username, String password,String smsCode, String randomStr);

    Response login(String phoneNumber, String password);
}
