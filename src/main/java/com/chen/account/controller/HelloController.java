package com.chen.account.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * author long
 * <p>
 * date 17-9-4
 * <p>
 * desc 调试页面
 */
@Controller
public class HelloController {
    private Logger logger = Logger.getLogger(HelloController.class);

    /*
     *   http://localhost:8080/hello?name=cn.7player
     */

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/forget")
    public String getPass() {
        return "forget_pass";
    }

    @RequestMapping("/signUp")
    public String signUp() {
        return "sign_up";
    }

    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    @RequestMapping("/stockAll")
    public String stockAll(){
        return "";
    }

    @RequestMapping("/home")
    public String home() {
        logger.info("hello");
        return "home";
    }

    @RequestMapping("/api")
    @ResponseBody
    public String greetin(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        logger.info("hello");
        model.addAttribute("name", name);
        return "hello" + name;
    }

}
