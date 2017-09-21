package com.chen.account.dao;

import com.chen.account.entity.StockData;

public interface StockDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(StockData record);

    int insertSelective(StockData record);

    StockData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(StockData record);

    int updateByPrimaryKey(StockData record);
}