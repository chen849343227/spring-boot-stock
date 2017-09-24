package com.chen.account.dao;

import com.chen.account.entity.StockOrder;

public interface StockOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(StockOrder record);

    int insertSelective(StockOrder record);

    StockOrder selectByPrimaryKey(Integer orderId);

    int updateByPrimaryKeySelective(StockOrder record);

    int updateByPrimaryKey(StockOrder record);
}