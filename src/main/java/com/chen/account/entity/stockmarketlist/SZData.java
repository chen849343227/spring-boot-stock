package com.chen.account.entity.stockmarketlist;

/**
 * author long
 * <p>
 * date 17-9-11
 * <p>
 * desc 深圳和上海股市列表data
 */
public class SZData {
    private String symbol;
    private String name;
    private String trade;
    private String pricechange;
    private String changepercent;
    private String buy;
    private String sell;
    private String settlement;
    private String open;
    private String high;
    private String low;
    private int volume;
    private int amount;
    private String code;
    private String ticktime;
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

    public void setTrade(String trade) {
        this.trade = trade;
    }
    public String getTrade() {
        return trade;
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

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }
    public String getSettlement() {
        return settlement;
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

    public void setVolume(int volume) {
        this.volume = volume;
    }
    public int getVolume() {
        return volume;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    public int getAmount() {
        return amount;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCode() {
        return code;
    }

    public void setTicktime(String ticktime) {
        this.ticktime = ticktime;
    }
    public String getTicktime() {
        return ticktime;
    }
}
