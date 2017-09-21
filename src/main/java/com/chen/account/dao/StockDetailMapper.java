package com.chen.account.dao;

import com.chen.account.entity.StockDetail;

public interface StockDetailMapper {
    int deleteByPrimaryKey(String stockId);

    int insert(StockDetail record);

    int insertSelective(StockDetail record);

    StockDetail selectByPrimaryKey(String stockId);

    int updateByPrimaryKeySelective(StockDetail record);

    int updateByPrimaryKey(StockDetail record);
}