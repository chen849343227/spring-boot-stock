package com.chen.account.service.impl;

import com.chen.account.constant.StockConstant;
import com.chen.account.dao.*;
import com.chen.account.entity.StockData;
import com.chen.account.entity.StockDetail;
import com.chen.account.entity.StockMarket;
import com.chen.account.entity.StockOrder;
import com.chen.account.service.IStockService;
import com.chen.common.http.entity.Response;
import com.chen.common.http.res.TransmitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

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
        int result = stockOrderMapperExtends.insert(order);
        if (result == 1) {
            //插入成功
            // TransmitUtils.transmitResponse(true,StockConstant.COMMIT_ORDER_SUCCESS,)
        }
        return null;
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
        if (stockData != null) {
            return TransmitUtils.transmitResponse(true, StockConstant.REQUEST_DATA_SUCCESS, stockData);
        } else {
            return TransmitUtils.transmitErrorResponse(StockConstant.REQUEST_DATA_NULL, StockConstant.CODE_STOCK_DATA_NULL, null);
        }
    }
}
