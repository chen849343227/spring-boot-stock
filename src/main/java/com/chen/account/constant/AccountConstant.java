package com.chen.account.constant;

/**
 * Created by chenglei on 2017/6/7.
 */
public interface AccountConstant {


    String SEND_SMS_CODE_SUCCESS="验证码发送成功";
    String SEND_SMS_CODE_FAIL ="验证码发送失败,请稍后重试";
    /**
     * 返回的message信息
     */
    String SIGN_UP_FAIL = "注册失败，请稍后重试";
    String SIGN_UP_SUCCESS = "注册成功";
    String SIGN_UP_USER_ALREADY_EXIST = "该手机号码已经被注册";

    String LOGIN_FAIL = "登录失败，请稍后重试";
    String LOGIN_SUCCESS= "登录成功";
    String LOGIN_PASSWORD_ERROR = "用户名密码错误";
    String LOGIN_USER_NOT_EXIST = "用户不存在";


    /**
     * 业务错误码啊
     */
    int CODE_SIGN_UP_FAIL = 10001;  //注册失败
    int CODE_SIGN_UP_ALREADY_EXIST = 10002;  //用户已经存在

    int CODE_LOGIN_FAIL = 10003; //登录失败
    int CODE_LOGIN_PASSWORD_ERROR = 10004; //用户名密码错误
    int CODE_LOGIN_USER_NOT_EXIST = 10005; //用户不存在
}
