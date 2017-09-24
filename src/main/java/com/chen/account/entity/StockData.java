package com.chen.account.entity;

public class StockData {
    private Integer id;

    private String phone;

    private String stockId;

    private String stockName;

    private String haveAmount;

    private String sellAmount;

    private String stockMoney;

    private String buyMoney;

    private String proAmount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getHaveAmount() {
        return haveAmount;
    }

    public void setHaveAmount(String haveAmount) {
        this.haveAmount = haveAmount;
    }

    public String getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(String sellAmount) {
        this.sellAmount = sellAmount;
    }

    public String getStockMoney() {
        return stockMoney;
    }

    public void setStockMoney(String stockMoney) {
        this.stockMoney = stockMoney;
    }

    public String getBuyMoney() {
        return buyMoney;
    }

    public void setBuyMoney(String buyMoney) {
        this.buyMoney = buyMoney;
    }

    public String getProAmount() {
        return proAmount;
    }

    public void setProAmount(String proAmount) {
        this.proAmount = proAmount;
    }
}