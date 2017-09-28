package com.chen.account.dao;

import com.chen.account.entity.StockDetail;

/**
 * author long
 * <p>
 * date 17-9-21
 * <p>
 * desc
 */
public interface StockDetailMapperExtends extends  StockDetailMapper {
        StockDetail selectByStockId(String stockId);
}
