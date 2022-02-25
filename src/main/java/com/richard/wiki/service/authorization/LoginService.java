package com.richard.wiki.service.authorization;

import com.richard.wiki.domain.UserInfo;

public interface LoginService {

    /**
     * 生成token
     * @param userInfo      传入的用户对象
     * @return              返回token
     */
    public String createToken(UserInfo userInfo);

    /**
     * 检查token校验是否有效
     * @param token         传入的token
     * @return              返回true or false
     */
    public boolean verifyToken(String token);

    /**
     * 根据token获取用户信息
     * @param token         传入的token
     * @return              返回用户信息
     */
    public UserInfo getTokenInfo(String token);

}
