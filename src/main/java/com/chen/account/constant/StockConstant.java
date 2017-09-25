package com.chen.account.constant;

/**
 * author long
 * <p>
 * date 17-9-11
 * <p>
 * desc
 */
public interface StockConstant {
    public static String host = "web.juhe.cn:8080";


    String REQUEST_DATA_SUCCESS = "请求数据成功";
    String REQUEST_DATA_NULL = "请求数据为空";
    String STOCK_ID_NULL = "股票代码为空";
    String COMMIT_ORDER_SUCCESSED = "订单提交成功";
    String COMMIT_ORDER_FAILED = "订单提交成功";



    int CODE_STOCK_DATA_NULL = 20001;   //数据为空
    int CODE_STOCK_ID_NULL = 20002;  // 股票ID为空
    int CODE_COMMIT_ORDER_FAIL = 20003; //订单提交失败
}
