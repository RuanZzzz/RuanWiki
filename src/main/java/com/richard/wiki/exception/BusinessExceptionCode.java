package com.richard.wiki.exception;

public enum BusinessExceptionCode {

    USER_LOGIN_NAME_EXIST("登录名已存在"),
    LOGIN_USER_ERROR("账号或密码不正确"),
    VOTE_REPEAT("您已经点赞过"),
    NO_LOGIN("用户未登录"),
    ERROR_AUTH("登录失效"),
    NO_USER_INFO("获取用户信息失败"),
    ERROR_LOGOUT("退出登录失败")
    ;

    private String desc;

    BusinessExceptionCode(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
