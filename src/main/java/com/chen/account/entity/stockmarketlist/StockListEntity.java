package com.chen.account.entity.stockmarketlist;

/**
 * author long
 * <p>
 * date 17-9-11
 * <p>
 * desc
 */
public class StockListEntity {

    private int error_code;  /*错误码*/
    private String reason;  /*SUCCESSED*/
    private Result result;  /*Result*/

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public int getError_code() {
        return error_code;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Result getResult() {
        return result;
    }


}
