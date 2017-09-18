package com.chen.account.entity;

/**
 * author long
 * <p>
 * date 17-9-16
 * <p>
 * desc
 */
public class VerifyCodeEntity {
    private String code;
    private String phone;
    private long createTime;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }
}
