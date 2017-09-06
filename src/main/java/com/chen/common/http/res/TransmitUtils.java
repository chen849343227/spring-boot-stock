package com.chen.common.http.res;


import com.chen.common.http.entity.Response;

/**
 * Created by chenglei on 2017/6/7.
 */
public class TransmitUtils {
    public static Response transmitResponse(boolean isSuccess, String message, Object entity) {
        int code = isSuccess ? 0 : 1;
        Response response = new Response();
        response.setSuccess(true);
        response.setCode(code);
        response.setMessage(message);
        response.setResponse(entity);
        return response;
    }


    public static Response transmitErrorResponse(String message, int errorCode, String errorMessage) {
        Response response = new Response();
        response.setSuccess(false);
        response.setCode(1);
        response.setMessage(message);
        Response.ErrorResponse errorResponse = new Response.ErrorResponse(errorCode, errorMessage);
        response.setErrorResponse(errorResponse);
        return response;
    }
}
