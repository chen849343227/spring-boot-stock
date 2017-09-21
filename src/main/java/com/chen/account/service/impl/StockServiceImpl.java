package com.chen.account.service.impl;

import com.chen.account.constant.StockConstant;
import com.chen.account.dao.StockDetailMapper;
import com.chen.account.dao.StockDetailMapperExtends;
import com.chen.account.dao.StockMarketMapperExtends;
import com.chen.account.entity.Order;
import com.chen.account.entity.StockDetail;
import com.chen.account.entity.StockMarket;
import com.chen.account.service.IStockService;
import com.chen.common.http.entity.Response;
import com.chen.common.http.res.TransmitUtils;
import org.springframework.beans.factory.annotation.Autowired;
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


    /**
     * 提交订单
     *
     * @param order 订单实体
     * @return
     */
    @Override
    public Response submitOrder(Order order) {

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
        return null;
    }
}
