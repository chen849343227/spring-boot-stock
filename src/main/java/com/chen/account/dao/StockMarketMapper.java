package com.chen.account.dao;

import com.chen.account.entity.StockMarket;

public interface StockMarketMapper {
    int deleteByPrimaryKey(String symbol);

    int insert(StockMarket record);

    int insertSelective(StockMarket record);

    StockMarket selectByPrimaryKey(String symbol);

    int updateByPrimaryKeySelective(StockMarket record);

    int updateByPrimaryKey(StockMarket record);
}