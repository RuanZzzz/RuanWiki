package com.richard.wiki.service.authorization;

import com.richard.wiki.domain.UserInfo;
import com.richard.wiki.req.UserLoginReq;

public interface LoginService {

    /**
     * 用户登录
     * @param req           传入的登录账号密码
     * @return              登录成功返回用户信息
     */
    public UserInfo login(UserLoginReq req);

    /**
     * 生成token（废弃）
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

    /**
     * 退出登录，并删除token
     * @param token         传入的token
     */
    public void deleteToken(String token);

}
