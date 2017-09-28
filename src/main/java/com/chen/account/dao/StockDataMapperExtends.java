package com.chen.account.dao;

import com.chen.account.entity.StockData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * author long
 * <p>
 * date 17-9-22
 * <p>
 * desc
 */
public interface StockDataMapperExtends extends StockDataMapper {

    List<StockData> selectByPhone(String phone);

    StockData selectByPhoneAndStockId(@Param("phone") String phone, @Param("stockId") String stockId);
}
