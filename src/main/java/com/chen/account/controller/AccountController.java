package com.chen.account.controller;

import com.chen.Application;
import com.chen.account.service.impl.AccountServiceImpl;
import com.chen.common.http.entity.Response;
import com.chen.common.http.res.TransmitUtils;
import com.chen.common.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import static com.chen.account.constant.AccountConstant.*;

/**
 * author long
 * <p>
 * date 17-9-4
 * <p>
 * desc
 */
@RestController
@RequestMapping(value = "/web/account")
public class AccountController {
    private static Logger logger = Logger.getLogger(AccountController.class);

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
        logger.info("register:" + "username=" + phoneNumber + ",password=" + password);
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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Response login(HttpServletRequest request) {
        String phoneNumber = request.getParameter("phoneNumber");
        String password = request.getParameter("password");
        logger.info("login:" + "username=" + phoneNumber + ",password=" + password);
        Response response;
        try {
            response = service.login(phoneNumber, password);
            if (response.getIsSuccess()) {
                HttpSession session = request.getSession();
                session.setAttribute("user", phoneNumber);
                System.out.println(phoneNumber + " " + "登录成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return TransmitUtils.transmitErrorResponse(LOGIN_FAIL, CODE_LOGIN_FAIL, LOGIN_FAIL);
        }
        logger.info(response);
        return response;
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.POST)
    public String logOut(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "index";
    }
}
