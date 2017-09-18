package com.chen.account.controller;

import com.chen.common.http.entity.Response;
import com.chen.common.utils.JuheDemo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * author long
 * <p>
 * date 17-9-11System.out.println
 * <p>
 * desc
 */
@RestController
@RequestMapping(value = "/stock")
public class StockMarketController {

    @RequestMapping(value = "/hkall", method = RequestMethod.POST)
    public Response getHKStockMartekList(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String page = request.getParameter("page");
        String type = request.getParameter("type");
        Response response = JuheDemo.requestHKStockMartekList(Integer.parseInt(page), Integer.parseInt(type));
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        return response;
    }

    @RequestMapping(value = "/hk", method = RequestMethod.POST)
    private Response getHKStockMartek(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String num = request.getParameter("num");
        Response response = null;
        if (num != null) {
            // int stockId = Integer.parseInt(num);
            response = JuheDemo.requestHKStockMartek(num);
        }
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        return response;
    }
}
