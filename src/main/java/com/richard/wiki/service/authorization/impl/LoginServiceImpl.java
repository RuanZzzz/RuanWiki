package com.richard.wiki.service.authorization.impl;

import com.alibaba.fastjson.JSON;
import com.richard.wiki.common.CommonConstant;
import com.richard.wiki.domain.User;
import com.richard.wiki.domain.UserExample;
import com.richard.wiki.domain.UserInfo;
import com.richard.wiki.exception.BusinessException;
import com.richard.wiki.exception.BusinessExceptionCode;
import com.richard.wiki.mapper.UserMapper;
import com.richard.wiki.req.UserLoginReq;
import com.richard.wiki.service.authorization.LoginService;
import com.richard.wiki.util.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserInfo login(UserLoginReq req) {
        // 对传过来的密码进行加密（因为数据库中存储的已经是两次加密的密码）
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));

        // 根据用户名查询对象
        User userDb = checkByLoginName(req.getLoginName());
        if (ObjectUtils.isEmpty(userDb)) {
            // 用户不存在（账号或密码不正确）
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }

        // 判断密码是否正确
        if (!userDb.getPassword().equals(req.getPassword())) {
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }

        // 生成token
        String token = String.valueOf(snowFlake.nextId());
        UserInfo userInfo = UserInfo.builder().loginName(userDb.getLoginName()).userName(userDb.getName()).userId(String.valueOf(userDb.getId())).token(token).build();
        // 存储用户信息进redis中
        redisTemplate.boundValueOps(CommonConstant.USER_TOKEN + CommonConstant.DELIMITER + token).set(userInfo,CommonConstant.DEFAULT_REDIS_EXPIRE_TIME, TimeUnit.HOURS);

        // 如果登录成功，返回用户信息（后续还需要返回用户头像）
        return userInfo;
    }

    /**
     * （废弃）
     * token的生成，格式为：CommonConstant.USER_TOKEN + CommonConstant.DELIMITER + token(token:雪花算法生成的随机字符串)
     * @param userInfo      传入的用户对象
     * @return
     */
    @Override
    public String createToken(UserInfo userInfo) {
        String token = String.valueOf(snowFlake.nextId());
        // 存储用户信息进redis中
        redisTemplate.boundValueOps(CommonConstant.USER_TOKEN + CommonConstant.DELIMITER + token).set(userInfo,CommonConstant.DEFAULT_REDIS_EXPIRE_TIME, TimeUnit.HOURS);

        return token;
    }

    @Override
    public boolean verifyToken(String token) {
        token = CommonConstant.USER_TOKEN + CommonConstant.DELIMITER + token;
//        String redisToken = (String) redisTemplate.boundValueOps(token).get();
//        UserInfo userInfo = JSON.parseObject(redisToken,UserInfo.class);
        UserInfo userInfo = (UserInfo) redisTemplate.boundValueOps(token).get();

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
//        String redisToken = (String) redisTemplate.boundValueOps(token).get();
//        UserInfo userInfo = JSON.parseObject(redisToken,UserInfo.class);
        UserInfo userInfo = (UserInfo) redisTemplate.boundValueOps(token).get();

        if (userInfo == null) {
            throw new BusinessException(BusinessExceptionCode.NO_USER_INFO);
        }

        return userInfo;
    }

    @Override
    public void deleteToken(String token) {
        token = CommonConstant.USER_TOKEN + CommonConstant.DELIMITER + token;
        boolean isDeleted = redisTemplate.delete(token);
        if (!isDeleted) {
            throw new BusinessException(BusinessExceptionCode.ERROR_LOGOUT);
        }
    }

    /**
     * 判断新增的时候，登录名是否已经存在
     * @param loginName 传入的登录名
     * @return 如果存在返回对象，否则返回null
     */
    public User checkByLoginName(String loginName) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andLoginNameEqualTo(loginName);
        List<User> userList = userMapper.selectByExample(userExample);
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }else {
            return userList.get(0);
        }
    }

}
