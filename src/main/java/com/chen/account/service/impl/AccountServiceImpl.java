package com.chen.account.service.impl;

import com.chen.account.constant.AccountConstant;
import com.chen.account.dao.UserMapperExtends;
import com.chen.account.entity.SendSmsResponse;
import com.chen.account.entity.User;
import com.chen.account.entity.VerifyCodeEntity;
import com.chen.account.service.IAccountService;
import com.chen.common.http.entity.Response;
import com.chen.common.http.res.TransmitUtils;
import com.chen.common.utils.AliyunMessageUtil;
import com.chen.common.utils.RandomUtil;
import com.chen.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * author long
 * <p>
 * date 17-9-4
 * <p>
 * desc
 */
@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private UserMapperExtends userMapper;

    //验证码的实体类
    private VerifyCodeEntity verifyCodeEntity;

    @Override
    public Response getVerifyCode(String phoneNumber) {
        //校验手机号
        /*if(phoneNumber){

        }*/
        //生成需要发送出去的短信验证码
        String code = RandomUtil.createRandomVcode();
        SendSmsResponse sendSmsResponse = AliyunMessageUtil.requestSmsCode(phoneNumber, code);
        SendSmsResponse.Result result;
        SendSmsResponse.Error_response errorResponse = null;
        try {
            result = sendSmsResponse.getAlibaba_aliqin_fc_sms_num_send_response().getResult();
            if (result.getSuccess()) {
                //当短信发送成功的时候,讲需要的信息保存在实体类中,方便后面注册时候核对信息
                verifyCodeEntity = new VerifyCodeEntity();
                verifyCodeEntity.setCode(code);
                verifyCodeEntity.setPhone(phoneNumber);
                verifyCodeEntity.setCreateTime(System.currentTimeMillis());
                return TransmitUtils.transmitResponse(true, AccountConstant.SEND_SMS_CODE_SUCCESS, phoneNumber);
            }
        } catch (NullPointerException e) {
            errorResponse = sendSmsResponse.getError_response();
        }
        return TransmitUtils.transmitErrorResponse(AccountConstant.SEND_SMS_CODE_FAIL, errorResponse.getCode(), errorResponse.getSub_msg());
    }

    @Override
    public Response register(String phoneNumber, String password, String smsCode, String randomStr) {
        //判断查询到的手机号是否为空,不为空就是账号已存在
        if (userMapper.selectByPhone(phoneNumber) != null) {
            return TransmitUtils.transmitErrorResponse(AccountConstant.SIGN_UP_USER_ALREADY_EXIST,
                    AccountConstant.CODE_SIGN_UP_ALREADY_EXIST, AccountConstant.SIGN_UP_USER_ALREADY_EXIST);
        }
        //设置验证码的有效期为两分钟之内
        if (System.currentTimeMillis() - verifyCodeEntity.getCreateTime() > 120000) {
            //时间超时,将存储的信息清空
            verifyCodeEntity = null;
            return TransmitUtils.transmitErrorResponse(AccountConstant.SIGN_UP_VERIFY_CODE_TIME_OUT,
                    AccountConstant.CODE_SIGN_UP_SMSCODE_TIME_OUT, AccountConstant.SIGN_UP_VERIFY_CODE_TIME_OUT);
        }
        //如果验证码相同并且手机号匹配才返回注册成功，不然返回注册错误
        if (smsCode.equals(verifyCodeEntity.getCode()) && phoneNumber.equals(verifyCodeEntity.getPhone())) {
            //这里开始加密密码
            password = StringUtils.md5(password, randomStr);
            User user = new User();
            long createAt = System.currentTimeMillis();
            user.setCreateat(createAt);
            user.setUpdateat(createAt);
            user.setPhone(phoneNumber);
            user.setPassword(password);
            user.setRandomstr(randomStr);
            int signUpRes = userMapper.insert(user);
            if (signUpRes == 1) {
                user.setPassword("");
                user.setRandomstr("");
                user.setUpdateat(0L);
                user.setCreateat(0L);
                return TransmitUtils.transmitResponse(true, AccountConstant.SIGN_UP_SUCCESS, user);
            } else {
                return TransmitUtils.transmitErrorResponse(AccountConstant.SIGN_UP_FAIL, AccountConstant.CODE_SIGN_UP_FAIL, AccountConstant.SIGN_UP_FAIL);
            }
        } else {
            //如果验证码和手机号有一个不匹配,直接返回验证码错误
            return TransmitUtils.transmitErrorResponse(AccountConstant.SIGN_UP_VERIFY_CODE_ERROR,
                    AccountConstant.CODE_SIGN_UP_SMSCODE_ERROR, AccountConstant.SIGN_UP_VERIFY_CODE_ERROR);
        }
    }

    @Override
    public Response login(String phoneNumber, String password) {
        User user = userMapper.selectByPhone(phoneNumber);
        //检测用户是否存在
        if (user == null) {
            System.out.println("用户不存在");
            return TransmitUtils.transmitErrorResponse(AccountConstant.LOGIN_FAIL, AccountConstant.CODE_LOGIN_USER_NOT_EXIST,
                    AccountConstant.LOGIN_USER_NOT_EXIST);
        }

        String randomStr = user.getRandomstr();
        String psd = StringUtils.md5(password, randomStr);

        //登录成功
        if (psd.equals(user.getPassword())) {
            user.setPassword("");
            user.setRandomstr("");
            return TransmitUtils.transmitResponse(true,
                    AccountConstant.LOGIN_SUCCESS, user);
        } else {
            //用户名密码不匹配
            return TransmitUtils.transmitErrorResponse(
                    AccountConstant.LOGIN_FAIL,
                    AccountConstant.CODE_LOGIN_PASSWORD_ERROR,
                    AccountConstant.LOGIN_PASSWORD_ERROR
            );
        }
    }
}
