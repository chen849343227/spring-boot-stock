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
import com.chen.common.utils.FormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
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
        Date currentTime = new Date();
        try {
            order.setStockTime(FormatUtil.formatDate(currentTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int result = stockOrderMapperExtends.insert(order);
        if (result == 1) {
            //插入成功
            return TransmitUtils.transmitResponse(true, StockConstant.COMMIT_ORDER_SUCCESSED, null);
        } else {
            return TransmitUtils.transmitErrorResponse(StockConstant.COMMIT_ORDER_FAILED, StockConstant.CODE_STOCK_DATA_NULL, null);
        }
    }

    @Override
    public Response getStockMarketList() {
        List<StockMarket> stockMarket = stockMarketMapper.getAll();
        if (stockMarket != null && stockMarket.size() != 0) {
            return TransmitUtils.transmitResponse(true, StockConstant.REQUEST_DATA_SUCCESS, stockMarket);
        } else {
            return TransmitUtils.transmitErrorResponse(StockConstant.REQUEST_DATA_NULL, StockConstant.CODE_COMMIT_ORDER_FAIL, null);
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

    /**
     * @param phone
     * @return
     */
    @Override
    public Response getOrder(String phone) {
        if (phone != null) {
            List<StockOrder> order = stockOrderMapperExtends.selectByPhone(phone);
            if (order != null && order.size() != 0) {
                return TransmitUtils.transmitResponse(true, StockConstant.REQUEST_DATA_SUCCESS, order);
            } else {
                return TransmitUtils.transmitErrorResponse(StockConstant.REQUEST_DATA_NULL, StockConstant.CODE_STOCK_DATA_NULL, null);
            }
        } else {
            return TransmitUtils.transmitErrorResponse(StockConstant.REQUEST_PARAMETER_ERROE, StockConstant.CODE_REQUEST_PARAMETER_ERROE, StockConstant.REQUEST_PARAMETER_ERROE);
        }
    }

    /**
     * 通过手机号和股票ID来筛选
     *
     * @param phone   手机号
     * @param stockId 股票ID
     * @return ${phone}持有的对应${stockId}的股票
     */
    @Override
    public Response getOrderAll(String phone, String stockId) {
        if (phone != null && stockId != null) {
            List<StockOrder> orders = stockOrderMapperExtends.selectByPhoneAndStockId(phone, stockId);
            if (orders != null && orders.size() != 0) {
                return TransmitUtils.transmitResponse(true, StockConstant.REQUEST_DATA_SUCCESS, orders);
            } else {
                return TransmitUtils.transmitErrorResponse(StockConstant.REQUEST_DATA_NULL, StockConstant.CODE_STOCK_DATA_NULL, null);
            }
        } else {
            return TransmitUtils.transmitErrorResponse(StockConstant.REQUEST_PARAMETER_ERROE, StockConstant.CODE_REQUEST_PARAMETER_ERROE, StockConstant.REQUEST_PARAMETER_ERROE);
        }
    }


    /**
     * 获取用户持股信息
     *
     * @param phone 通过手机号拿到用户持股
     * @return
     */
    @Override
    public Response getUserStockData(String phone) {
        List<StockData> stockData = stockDataMapperExtends.selectByPhone(phone);
        if (stockData != null && stockData.size() != 0) {
            return TransmitUtils.transmitResponse(true, StockConstant.REQUEST_DATA_SUCCESS, stockData);
        } else {
            return TransmitUtils.transmitErrorResponse(StockConstant.REQUEST_DATA_NULL, StockConstant.CODE_STOCK_DATA_NULL, null);
        }
    }
}
