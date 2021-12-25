package com.richard.wiki.config;

import com.richard.wiki.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    // 增加需要的拦截器，将需要的拦截器注入进来
    @Resource
    LogInterceptor logInterceptor;

    /**
     * 增加拦截器的方法
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(logInterceptor) // 注册拦截器
                .addPathPatterns("/**").excludePathPatterns("/login");  // 针对所有的请求（除了登录接口以外）
    }

}
