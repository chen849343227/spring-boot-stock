package com.chen.account.entity.stockmarketlist;

import java.util.List;

/**
 * author long
 * <p>
 * date 17-9-11
 * <p>
 * desc
 */
public class Result<T> {

    private String totalCount; /*总条数*/
    private String page;  /*当前页数*/
    private String num;   /*显示条数*/
    private List<T> data;

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPage() {
        return page;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }
    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

}
