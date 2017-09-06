package com.chen.account.entity;

public class SendSmsResponse {
    //成功结果
    private Alibaba_aliqin_fc_sms_num_send_response alibaba_aliqin_fc_sms_num_send_response;

    //错误结果
    private Error_response error_response;


    public void setAlibaba_aliqin_fc_sms_num_send_response(
            Alibaba_aliqin_fc_sms_num_send_response alibaba_aliqin_fc_sms_num_send_response) {
        this.alibaba_aliqin_fc_sms_num_send_response = alibaba_aliqin_fc_sms_num_send_response;
    }

    public Alibaba_aliqin_fc_sms_num_send_response getAlibaba_aliqin_fc_sms_num_send_response() {
        return this.alibaba_aliqin_fc_sms_num_send_response;
    }

    public void setError_response(Error_response error_response){
        this.error_response = error_response;
    }
    public Error_response getError_response(){
        return this.error_response;
    }

    public class Alibaba_aliqin_fc_sms_num_send_response {
        private Result result;

        private String request_id;

        public void setResult(Result result) {
            this.result = result;
        }

        public Result getResult() {
            return this.result;
        }

        public void setRequest_id(String request_id) {
            this.request_id = request_id;
        }

        public String getRequest_id() {
            return this.request_id;
        }

    }

    public class Result {
        private String err_code;

        private String model;

        private boolean success;

        public void setErr_code(String err_code) {
            this.err_code = err_code;
        }

        public String getErr_code() {
            return this.err_code;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getModel() {
            return this.model;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public boolean getSuccess() {
            return this.success;
        }

    }

    public class Error_response {
        private int code;

        private String msg;

        private String sub_code;

        private String sub_msg;

        private String request_id;

        public void setCode(int code){
            this.code = code;
        }
        public int getCode(){
            return this.code;
        }
        public void setMsg(String msg){
            this.msg = msg;
        }
        public String getMsg(){
            return this.msg;
        }
        public void setSub_code(String sub_code){
            this.sub_code = sub_code;
        }
        public String getSub_code(){
            return this.sub_code;
        }
        public void setSub_msg(String sub_msg){
            this.sub_msg = sub_msg;
        }
        public String getSub_msg(){
            return this.sub_msg;
        }
        public void setRequest_id(String request_id){
            this.request_id = request_id;
        }
        public String getRequest_id(){
            return this.request_id;
        }

    }
}
