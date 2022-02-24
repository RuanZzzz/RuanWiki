package com.richard.wiki.controller;

import com.richard.wiki.common.CommonConstant;
import com.richard.wiki.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * 基类：用于获取用户信息
 */
public class BaseController {

    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 获取当前用户信息
     * @return  返回当前用户
     */
    protected UserInfo getCurrentUser() {
        return (UserInfo) httpServletRequest.getAttribute(CommonConstant.LOGIN_USER_INFO);
    }

    /**
     * 获取token
     * @return 返回当前请求的token
     */
    protected String getToken() {
        return httpServletRequest.getHeader(CommonConstant.USER_TOKEN);
    }

}
