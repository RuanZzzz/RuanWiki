package com.richard.wiki.service.authorization.impl;

import com.alibaba.fastjson.JSON;
import com.richard.wiki.common.CommonConstant;
import com.richard.wiki.domain.UserInfo;
import com.richard.wiki.exception.BusinessException;
import com.richard.wiki.exception.BusinessExceptionCode;
import com.richard.wiki.service.authorization.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * token的生成，格式为：CommonConstant.USER_TOKEN + CommonConstant.DELIMITER + token(token:雪花算法生成的随机字符串)
     * @param userInfo      传入的用户对象
     * @return
     */
    @Override
    public String createToken(UserInfo userInfo) {
        // TODO：token的生成

        return null;
    }

    @Override
    public boolean verifyToken(String token) {
        token = CommonConstant.USER_TOKEN + CommonConstant.DELIMITER + token;
        String redisToken = (String) redisTemplate.boundValueOps(token).get();
        UserInfo userInfo = JSON.parseObject(redisToken,UserInfo.class);

        // 如果token无效
        if (userInfo == null) {
            return false;
        }

        // 如果token有效，延长token时间
        redisTemplate.boundValueOps(token).expire(CommonConstant.DEFAULT_REDIS_EXPIRE_TIME, TimeUnit.HOURS);
        return true;
    }

    @Override
    public UserInfo getTokenInfo(String token) {
        token = CommonConstant.USER_TOKEN + CommonConstant.DELIMITER + token;
        String redisToken = (String) redisTemplate.boundValueOps(token).get();
        UserInfo userInfo = JSON.parseObject(redisToken,UserInfo.class);
        if (userInfo == null) {
            throw new BusinessException(BusinessExceptionCode.NO_USER_INFO);
        }

        return userInfo;
    }


}
