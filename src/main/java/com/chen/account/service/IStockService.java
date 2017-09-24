package com.chen.account.service;

import com.chen.account.entity.StockOrder;
import com.chen.common.http.entity.Response;

/**
 * author long
 * <p>
 * date 17-9-20
 * <p>
 * desc
 */
public interface IStockService {

    Response submitOrder(StockOrder order);

    Response getStockMarketList();

    Response getStockDetail(String stockId);

    Response getOrder(String phone);

    Response getUserStockData(String phone);
}
