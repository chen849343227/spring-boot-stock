package com.chen.account.entity;

import java.util.Date;

public class StockOrder {
    private Integer orderId;  //订单Id

    private String stockId;  //股票Id

    private String stockName;  //股票名称

    private String user;   //用户手机号

    private Double orderPrice;  //价格

    private Integer orderState;  //状态  0(等待成交) or 1 （已成交）

    private Integer orderType;  //类型  1(卖) or 0（买）

    private Date stockTime;    //时间

    private Integer amount;  //数量

    private Date matchTime;  //成交时间   也就是撮合时间

    private Double matchPrice;

    private Integer matchAmount;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Integer getOrderState() {
        return orderState;
    }

    public void setOrderState(Integer orderState) {
        this.orderState = orderState;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Date getStockTime() {
        return stockTime;
    }

    public void setStockTime(Date stockTime) {
        this.stockTime = stockTime;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getMatchTime() {
        return matchTime;
    }

    public void setMatchTime(Date matchTime) {
        this.matchTime = matchTime;
    }

    public Double getMatchPrice() {
        return matchPrice;
    }

    public void setMatchPrice(Double matchPrice) {
        this.matchPrice = matchPrice;
    }

    public Integer getMatchAmount() {
        return matchAmount;
    }

    public void setMatchAmount(Integer matchAmount) {
        this.matchAmount = matchAmount;
    }
}