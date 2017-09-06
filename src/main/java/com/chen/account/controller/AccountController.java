package com.chen.account.controller;

import com.chen.account.service.impl.AccountServiceImpl;
import com.chen.common.http.entity.Response;
import com.chen.common.http.res.TransmitUtils;
import com.chen.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;

import static com.chen.account.constant.AccountConstant.CODE_SIGN_UP_FAIL;
import static com.chen.account.constant.AccountConstant.SIGN_UP_FAIL;

/**
 * author long
 * <p>
 * date 17-9-4
 * <p>
 * desc
 */
@RestController
@RequestMapping(value = "/api/account")
public class AccountController {

    @Autowired
    private AccountServiceImpl service;

    @RequestMapping(value = "/getVerifyCode", method = RequestMethod.POST)
    public Response getVerifyCode(HttpServletRequest request) {
        String phoneNumber = request.getParameter("phone");
        return service.getVerifyCode(phoneNumber);
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(HttpServletRequest request) {
        String phoneNumber = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("username=" + phoneNumber + ",password=" + password);
        String randomStr = StringUtils.getRandomString(8);


        Response response;
        try {
            response = service.register(phoneNumber, password, randomStr);
        } catch (Exception e) {
            e.printStackTrace();
            return TransmitUtils.transmitErrorResponse(SIGN_UP_FAIL, CODE_SIGN_UP_FAIL, SIGN_UP_FAIL);
        }
        return response;
    }
}
