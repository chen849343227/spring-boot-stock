package com.chen.account.entity;

public class User {
    private Integer userId;

    private String userName;

    private String userPwd;

    private String userRandom;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserRandom() {
        return userRandom;
    }

    public void setUserRandom(String userRandom) {
        this.userRandom = userRandom;
    }
}