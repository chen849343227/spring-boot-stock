package com.chen.account.dao;

import com.chen.account.entity.StockOrder;

import java.util.List;

/**
 * author long
 * <p>
 * date 17-9-21
 * <p>
 * desc
 */
public interface StockOrderMapperExtends extends StockOrderMapper {

    List<StockOrder> selectByPhone(String phone);

    List<StockOrder> selectByStockId(String stockId);

    List<StockOrder> getAll();

    List<StockOrder> getStockId();

}
