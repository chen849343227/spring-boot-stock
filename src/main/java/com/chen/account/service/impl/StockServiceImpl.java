package com.chen.account.service.impl;

import com.chen.account.constant.StockConstant;
import com.chen.account.controller.StockMarketController;
import com.chen.account.dao.*;
import com.chen.account.entity.StockData;
import com.chen.account.entity.StockDetail;
import com.chen.account.entity.StockMarket;
import com.chen.account.entity.StockOrder;
import com.chen.account.service.IStockService;
import com.chen.common.http.entity.Response;
import com.chen.common.http.res.TransmitUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * author long
 * <p>
 * date 17-9-20
 * <p>
 * desc
 */
@Service
public class StockServiceImpl implements IStockService {
    private static Logger logger = Logger.getLogger(StockServiceImpl.class);
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private StockDetailMapperExtends stockDetailMapper;

    @Autowired
    private StockMarketMapperExtends stockMarketMapper;

    @Autowired
    private StockOrderMapperExtends stockOrderMapperExtends;

    @Autowired
    private StockDataMapperExtends stockDataMapperExtends;


    /**
     * 提交订单
     *
     * @param order 订单实体
     * @return
     */
    @Override
    public Response submitOrder(StockOrder order) {
        order.setOrderState(1);
        //转换时间格式
        Date currentTime = new Date(System.currentTimeMillis());
        String dateString = formatter.format(currentTime);
        Date currentTime_2 = null;
        try {
            currentTime_2 = formatter.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        logger.info(dateString);
        order.setStockTime(currentTime_2);
        logger.info(order);
        int result = stockOrderMapperExtends.insert(order);
        if (result == 1) {
            //插入成功
            return TransmitUtils.transmitResponse(true, StockConstant.COMMIT_ORDER_SUCCESSED, order);
        } else {
            return TransmitUtils.transmitErrorResponse(StockConstant.COMMIT_ORDER_FAILED, StockConstant.CODE_STOCK_DATA_NULL, null);
        }
    }

    @Override
    public Response getStockMarketList() {
        List<StockMarket> stockMarket = stockMarketMapper.getAll();
        if (stockMarket != null) {
            return TransmitUtils.transmitResponse(true, StockConstant.REQUEST_DATA_SUCCESS, stockMarket);
        } else {
            return TransmitUtils.transmitErrorResponse(StockConstant.REQUEST_DATA_NULL, StockConstant.CODE_STOCK_DATA_NULL, null);
        }
    }

    @Override
    public Response getStockDetail(String stockId) {
        if (stockId == null) {
            TransmitUtils.transmitErrorResponse(StockConstant.STOCK_ID_NULL, StockConstant.CODE_STOCK_ID_NULL, StockConstant.STOCK_ID_NULL);
        }
        StockDetail stockDetail = stockDetailMapper.selectByPrimaryKey(stockId);
        if (stockDetail != null) {
            return TransmitUtils.transmitResponse(true, StockConstant.REQUEST_DATA_SUCCESS, stockDetail);
        } else {
            return TransmitUtils.transmitErrorResponse(StockConstant.REQUEST_DATA_NULL, StockConstant.CODE_STOCK_DATA_NULL, null);
        }
    }

    @Override
    public Response getOrder(String phone) {
        List<StockOrder> order = stockOrderMapperExtends.selectByPhone(phone);
        if (order != null) {
            return TransmitUtils.transmitResponse(true, StockConstant.REQUEST_DATA_SUCCESS, order);
        } else {
            return TransmitUtils.transmitErrorResponse(StockConstant.REQUEST_DATA_NULL, StockConstant.CODE_STOCK_DATA_NULL, null);
        }
    }

    @Override
    public Response getUserStockData(String phone) {
        List<StockData> stockData = stockDataMapperExtends.selectByPhone(phone);
        System.out.println(stockData.size());
        if (stockData != null) {
            return TransmitUtils.transmitResponse(true, StockConstant.REQUEST_DATA_SUCCESS, stockData);
        } else {
            return TransmitUtils.transmitErrorResponse(StockConstant.REQUEST_DATA_NULL, StockConstant.CODE_STOCK_DATA_NULL, null);
        }
    }
}
