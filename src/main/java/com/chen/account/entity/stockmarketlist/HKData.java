package com.chen.account.entity.stockmarketlist;

/**
 * author long
 * <p>
 * date 17-9-11
 * <p>
 * desc
 */
public class HKData {
    private String symbol; /*代码*/
    private String name;/*名称*/
    private String engname;/*英文名*/
    private String tradetype;
    private String lasttrade;/*昨收*/
    private String prevclose;/*昨收*/
    private String open;/*今开*/
    private String high;/*最高*/
    private String low;/*最低*/
    private String volume;/*成交量*/
    private String currentvolume;
    private String amount;/*成交额*/
    private String ticktime;/*时间*/
    private String buy;/*买入*/
    private String sell;/*卖出*/
    private String high_52week;/*52周最高*/
    private String low_52week;/*52周最低*/
    private String eps;
    private String dividend;
    private String stocks_sum;/*总股本*/
    private String pricechange;/*涨跌额*/
    private String changepercent;/*涨跌幅*/

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEngname(String engname) {
        this.engname = engname;
    }

    public String getEngname() {
        return engname;
    }

    public void setTradetype(String tradetype) {
        this.tradetype = tradetype;
    }

    public String getTradetype() {
        return tradetype;
    }

    public void setLasttrade(String lasttrade) {
        this.lasttrade = lasttrade;
    }

    public String getLasttrade() {
        return lasttrade;
    }

    public void setPrevclose(String prevclose) {
        this.prevclose = prevclose;
    }

    public String getPrevclose() {
        return prevclose;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getOpen() {
        return open;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getHigh() {
        return high;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getLow() {
        return low;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getVolume() {
        return volume;
    }

    public void setCurrentvolume(String currentvolume) {
        this.currentvolume = currentvolume;
    }

    public String getCurrentvolume() {
        return currentvolume;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public void setTicktime(String ticktime) {
        this.ticktime = ticktime;
    }

    public String getTicktime() {
        return ticktime;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getBuy() {
        return buy;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getSell() {
        return sell;
    }

    public void setHigh_52week(String high_52week) {
        this.high_52week = high_52week;
    }

    public String getHigh_52week() {
        return high_52week;
    }

    public void setLow_52week(String low_52week) {
        this.low_52week = low_52week;
    }

    public String getLow_52week() {
        return low_52week;
    }

    public void setEps(String eps) {
        this.eps = eps;
    }

    public String getEps() {
        return eps;
    }

    public void setDividend(String dividend) {
        this.dividend = dividend;
    }

    public String getDividend() {
        return dividend;
    }

    public void setStocks_sum(String stocks_sum) {
        this.stocks_sum = stocks_sum;
    }

    public String getStocks_sum() {
        return stocks_sum;
    }

    public void setPricechange(String pricechange) {
        this.pricechange = pricechange;
    }

    public String getPricechange() {
        return pricechange;
    }

    public void setChangepercent(String changepercent) {
        this.changepercent = changepercent;
    }

    public String getChangepercent() {
        return changepercent;
    }
}
