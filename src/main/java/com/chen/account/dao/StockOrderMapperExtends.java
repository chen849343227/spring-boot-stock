package com.chen.account.dao;

import com.chen.account.entity.StockOrder;
import org.apache.ibatis.annotations.Param;

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

    List<StockOrder> selectByPhoneAndStockId(@Param("user") String phone,@Param("stockId") String stockId);

    List<StockOrder> selectByStockId(String stockId);

    List<StockOrder> getAll();

    List<StockOrder> getStockId();

}
