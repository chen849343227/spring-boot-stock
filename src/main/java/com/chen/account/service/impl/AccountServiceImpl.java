package com.chen.account.service.impl;

import com.chen.account.constant.AccountConstant;
import com.chen.account.dao.UserMapper;
import com.chen.account.entity.SendSmsResponse;
import com.chen.account.entity.User;
import com.chen.account.service.IAccountService;
import com.chen.common.http.entity.Response;
import com.chen.common.http.res.TransmitUtils;
import com.chen.common.utils.AliyunMessageUtil;
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
    private UserMapper userMapper;

    @Override
    public Response getVerifyCode(String phone) {
        SendSmsResponse sendSmsResponse = AliyunMessageUtil.requestSmsCode(phone);
        SendSmsResponse.Result result;
        SendSmsResponse.Error_response errorResponse = null;
        try {
            result = sendSmsResponse.getAlibaba_aliqin_fc_sms_num_send_response().getResult();
            if (result.getSuccess()) {
                return TransmitUtils.transmitResponse(true, AccountConstant.SEND_SMS_CODE_SUCCESS, null);
            }
        } catch (NullPointerException e) {
            errorResponse = sendSmsResponse.getError_response();
        }
        return TransmitUtils.transmitErrorResponse(AccountConstant.SEND_SMS_CODE_FAIL, errorResponse.getCode(), errorResponse.getSub_msg());
    }

    @Override
    public Response register(String phone, String pwd, String randomStr) {
        //判断查询到的手机号是否为空,不为空就是账号已存在
        if (userMapper.selectByPhone(phone) != null) {
            return TransmitUtils.transmitErrorResponse(AccountConstant.SIGN_UP_USER_ALREADY_EXIST,
                    AccountConstant.CODE_SIGN_UP_ALREADY_EXIST, AccountConstant.SIGN_UP_USER_ALREADY_EXIST);
        }
        //这里开始加密密码
        pwd = StringUtils.md5(pwd, randomStr);
        User user = new User();
        user.setUserName(phone);
        user.setUserPwd(pwd);
        user.setUserRandom(randomStr);
        int signUpRes = userMapper.insert(user);
        if (signUpRes == 1) {
            user.setUserPwd("");
            user.setUserRandom("");
            return TransmitUtils.transmitResponse(true, AccountConstant.SIGN_UP_SUCCESS, user);
        } else {
            return TransmitUtils.transmitErrorResponse(AccountConstant.SIGN_UP_FAIL, AccountConstant.CODE_SIGN_UP_FAIL, AccountConstant.SIGN_UP_FAIL);
        }
    }

    @Override
    public Response login(String username, String pwd) {
        return null;
    }
}
