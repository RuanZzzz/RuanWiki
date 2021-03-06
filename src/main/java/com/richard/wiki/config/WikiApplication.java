package com.richard.wiki.config;

import com.richard.wiki.mapper.UserTokenMapper;
import com.richard.wiki.websocket.WebSocketServer;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com.richard")
@SpringBootApplication
@MapperScan("com.richard.wiki.mapper")
@EnableScheduling
@EnableAsync
public class WikiApplication {

    private static final Logger LOG = LoggerFactory.getLogger(WikiApplication.class);

    public static void main(String[] args) {
        //SpringApplication.run(WikiApplication.class, args);

        SpringApplication app = new SpringApplication(WikiApplication.class);
        ApplicationContext applicationContext = app.run(args);
        Environment env = applicationContext.getEnvironment();
        //Environment env = app.run(args).getEnvironment();
        LOG.info("启动成功！");
        LOG.info("地址：\thttp://127.0.0.1:{}", env.getProperty("server.port"));

        // 获取Spring IOC容器中的Service并注入
        UserTokenMapper userTokenMapper = applicationContext.getBean(UserTokenMapper.class);
        WebSocketServer.setUserTokenMapper(userTokenMapper);

    }

}
