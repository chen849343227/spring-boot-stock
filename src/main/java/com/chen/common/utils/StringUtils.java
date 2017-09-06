package com.chen.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * author long
 * <p>
 * date 17-9-4
 * <p>
 * desc
 */
public class StringUtils {
    //获取指定位数的随机字符串(包含小写字母、大写字母、数字,0<length)
    public static String getRandomString(int length) {
        //随机字符串的随机字符库
        String KeyString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer sb = new StringBuffer();
        int len = KeyString.length();
        for (int i = 0; i < length; i++) {
            sb.append(KeyString.charAt((int) Math.round(Math.random() * (len - 1))));
        }
        return sb.toString();
    }

    /**
     * 根据一个字符串和一个随机字符串做md5加密
     *
     * @param str
     * @param randomStr
     * @return
     */
    public static String md5(String str, String randomStr) {
        BigInteger md5Result = new BigInteger("00000000");
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update((str + randomStr).getBytes());
            md5Result = new BigInteger(1, messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return md5Result.toString();
    }

    /**
     * str是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }
}
