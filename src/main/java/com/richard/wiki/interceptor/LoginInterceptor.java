package com.richard.wiki.interceptor;

import com.richard.wiki.common.CommonConstant;
import com.richard.wiki.exception.BusinessException;
import com.richard.wiki.exception.BusinessExceptionCode;
import com.richard.wiki.service.authorization.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 打印请求信息
        LOG.info("------------- LoginInterceptor 开始 -------------");
        long startTime = System.currentTimeMillis();
        request.setAttribute("requestStartTime", startTime);

        // OPTIONS请求不做校验,
        if(request.getMethod().toUpperCase().equals("OPTIONS")){
            return true;
        }

        String token = request.getHeader(CommonConstant.USER_TOKEN);

        // 如果token不存在，则进行提示
        if (ObjectUtils.isEmpty(token)) {
            throw new BusinessException(BusinessExceptionCode.NO_LOGIN);
        }

        // 如果获取到token，则对token进行校验
        if (!loginService.verifyToken(token)) {
            throw new BusinessException(BusinessExceptionCode.ERROR_AUTH);
        }

        // 如果token校验通过，根据token获取用户信息并存储
        request.setAttribute(CommonConstant.LOGIN_USER_INFO,loginService.getTokenInfo(token));

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        long startTime = (Long) request.getAttribute("requestStartTime");
        LOG.info("------------- LoginInterceptor 结束 耗时：{} ms -------------", System.currentTimeMillis() - startTime);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        LOG.info("LogInterceptor 结束");
    }

}
