package com.chen.account.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * author long
 * <p>
 * date 17-9-4
 * <p>
 * desc
 */
@Controller
public class HelloController {
    private Logger logger = Logger.getLogger(HelloController.class);

    /*
     *   http://localhost:8080/hello?name=cn.7player
     */

    @RequestMapping("/hello")
    public String greeting() {
        logger.info("hello");
        return "index";
    }

    @RequestMapping("/ap")
    @ResponseBody
    public String greetin(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        logger.info("hello");
        model.addAttribute("name", name);
        return "hello" + name;
    }
}
