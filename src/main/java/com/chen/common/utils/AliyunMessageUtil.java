package com.chen.common.utils;

import com.chen.account.entity.SendSmsResponse;
import com.google.gson.Gson;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 阿里云短信发送工具类
 */
public class AliyunMessageUtil {

    private static SendSmsResponse sendSmsResponse;

    /**
     * 请求阿里云短信接口
     * @param phone 手机号
     * @param code 需要发送的验证码
     * @return 短信发送的结果
     */
    public static SendSmsResponse requestSmsCode(String phone,String code) {
        Gson gson = new Gson();
        // 服务url
        String url = "http://gw.api.taobao.com/router/rest";
        // appkey
        String appkey = "23567754";
        // secret
        String secret = "d02bd556928889a88b811aa28a9ec5c7";
        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("1");
        req.setSmsType("normal");
        req.setSmsFreeSignName("爱生活爱龙哥");
        req.setSmsParamString("{number:'" + code + " '}");
        req.setRecNum(phone);
        req.setSmsTemplateCode("SMS_60130168");
        AlibabaAliqinFcSmsNumSendResponse rsp;
        try {
            rsp = client.execute(req);
            sendSmsResponse = gson.fromJson(rsp.getBody(), SendSmsResponse.class);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return sendSmsResponse;
    }

}
