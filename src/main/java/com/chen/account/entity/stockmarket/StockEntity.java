package com.chen.account.entity.stockmarket;
import java.util.List;

/**
 * author long
 * <p>
 * date 17-9-14
 * <p>
 * desc
 */
public class StockEntity {

        private String resultcode;
        private String reason;
        private List<Result> result;
        private int error_code;
        public void setResultcode(String resultcode) {
            this.resultcode = resultcode;
        }
        public String getResultcode() {
            return resultcode;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
        public String getReason() {
            return reason;
        }

        public void setResult(List<Result> result) {
            this.result = result;
        }
        public List<Result> getResult() {
            return result;
        }

        public void setError_code(int error_code) {
            this.error_code = error_code;
        }
        public int getError_code() {
            return error_code;
        }
}
