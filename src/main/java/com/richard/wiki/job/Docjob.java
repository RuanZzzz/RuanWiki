package com.richard.wiki.job;

import com.richard.wiki.interceptor.LoginInterceptor;
import com.richard.wiki.service.DocService;
import com.richard.wiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Docjob {

    private static final Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private DocService docService;

    @Autowired
    private SnowFlake snowFlake;

    /**
     * 每天凌晨2点更新：0 0 2 * * ?
     * 每天晚上21:15更新：0 15 21 * * ?（用于测试）
     */
    @Scheduled(cron = "0 15 21 * * ?")
    public void cron()  {
        // 增加日志流水号
        MDC.put("LOG_ID",String.valueOf(snowFlake.nextId()));

        LOG.info("更新电子书下的文档数据开始");
        long start = System.currentTimeMillis();
        docService.updateEbookInfo();
        LOG.info("更新电子书下的文档数据结束，耗时：{}毫秒",System.currentTimeMillis() - start);
    }

}
