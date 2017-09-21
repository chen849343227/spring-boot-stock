package com.chen.account.entity;

import java.sql.Time;
import java.util.Date;

public class StockDetail {
    private String stockId;  //股票Id

    private String name;  //股票名称

    private String openPri;   //今开

    private String formPri;  //昨开

    private String maxPri;   //最高

    private String minPri;  //最低价

    private String lastestPri;    //最新价

    private String upPic;  //涨跌

    private String stockLimit;  //涨跌幅

    private String inPic;  //买入价

    private String outPic;  //卖出价

    private String traAmount;  //成交额

    private String traNumber;   //成交量（股）

    private String priearn;  //市盈率

    private Long stockDate;  //日期

    private Time stockTime;  //时间

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOpenPri() {
        return openPri;
    }

    public void setOpenPri(String openPri) {
        this.openPri = openPri;
    }

    public String getFormPri() {
        return formPri;
    }

    public void setFormPri(String formPri) {
        this.formPri = formPri;
    }

    public String getMaxPri() {
        return maxPri;
    }

    public void setMaxPri(String maxPri) {
        this.maxPri = maxPri;
    }

    public String getMinPri() {
        return minPri;
    }

    public void setMinPri(String minPri) {
        this.minPri = minPri;
    }

    public String getLastestPri() {
        return lastestPri;
    }

    public void setLastestPri(String lastestPri) {
        this.lastestPri = lastestPri;
    }

    public String getUpPic() {
        return upPic;
    }

    public void setUpPic(String upPic) {
        this.upPic = upPic;
    }

    public String getLimit() {
        return stockLimit;
    }

    public void setLimit(String stockLimit) {
        this.stockLimit = stockLimit;
    }

    public String getInPic() {
        return inPic;
    }

    public void setInPic(String inPic) {
        this.inPic = inPic;
    }

    public String getOutPic() {
        return outPic;
    }

    public void setOutPic(String outPic) {
        this.outPic = outPic;
    }

    public String getTraAmount() {
        return traAmount;
    }

    public void setTraAmount(String traAmount) {
        this.traAmount = traAmount;
    }

    public String getTraNumber() {
        return traNumber;
    }

    public void setTraNumber(String traNumber) {
        this.traNumber = traNumber;
    }

    public String getPriearn() {
        return priearn;
    }

    public void setPriearn(String priearn) {
        this.priearn = priearn;
    }

    public Long getDate() {
        return stockDate;
    }

    public void setDate(Long stockDate) {
        this.stockDate = stockDate;
    }

    public Date getTime() {
        return stockTime;
    }

    public void setTime(Time stockTime) {
        this.stockTime = stockTime;
    }
}