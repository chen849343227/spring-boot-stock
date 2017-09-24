package com.chen.account.dao;

import com.chen.account.entity.StockData;

import java.util.List;

/**
 * author long
 * <p>
 * date 17-9-22
 * <p>
 * desc
 */
public interface StockDataMapperExtends extends  StockDataMapper {

    List<StockData> selectByPhone(String phone);
}
