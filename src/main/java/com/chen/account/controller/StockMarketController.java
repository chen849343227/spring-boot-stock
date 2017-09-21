package com.chen.account.controller;

import com.chen.account.entity.Order;
import com.chen.account.service.impl.StockServiceImpl;
import com.chen.common.http.entity.Response;
import com.chen.common.utils.JuheDemo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private StockServiceImpl service;

    @RequestMapping(value = "/all", method = RequestMethod.POST)
    public Response getStockMarketList(HttpServletRequest request) {
        return service.getStockMarketList();
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    public Response getStockDetail(HttpServletRequest request) {
        String stockId = request.getParameter("stockId");
        return service.getStockDetail(stockId);
    }


    @PostMapping("/commitOrder")
    public Response commitOrder(Order order) {
        // return service.submitOrder(order);
        return null;
    }


    /**
     * 这个是调用聚合数据
     *
     * @param request
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/hkall", method = RequestMethod.POST)
    public Response getHKStockMarketList(HttpServletRequest request, HttpServletResponse httpServletResponse) {
        String page = request.getParameter("page");
        String type = request.getParameter("type");
        Response response = JuheDemo.requestHKStockMartekList(Integer.parseInt(page), Integer.parseInt(type));
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        return response;
    }

    /**
     * 这个是调用聚合数据
     *
     * @param request
     * @param httpServletResponse
     * @return
     */
    @RequestMapping(value = "/hk", method = RequestMethod.POST)
    private Response getHKStockMarket(HttpServletRequest request, HttpServletResponse httpServletResponse) {
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
