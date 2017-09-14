package com.chen.common.http.entity;

public class Response {
    private boolean isSuccess;
    private int code;
    private String message;
    private ErrorResponse errorResponse;
    private Object response;

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public static final class ErrorResponse {
        private int code;
        private String message;

        public ErrorResponse(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }


    @Override
    public String toString() {
        return "Response{" +
                "isSuccess=" + isSuccess +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", errorResponse=" + errorResponse +
                ", response=" + response +
                '}';
    }
}
