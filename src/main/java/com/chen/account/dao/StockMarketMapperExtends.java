package com.chen.account.dao;

import com.chen.account.entity.StockMarket;

import java.util.List;

/**
 * author long
 * <p>
 * date 17-9-20
 * <p>
 * desc
 */
public interface StockMarketMapperExtends extends StockMarketMapper {
    List<StockMarket> getAll();
}
