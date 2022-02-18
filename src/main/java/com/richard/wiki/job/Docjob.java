package com.richard.wiki.job;

import com.richard.wiki.interceptor.LoginInterceptor;
import com.richard.wiki.service.DocService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Docjob {

    private static final Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);

    @Autowired
    private DocService docService;

    /**
     * 每30s更新电子书信息
     */
    @Scheduled(cron = "5/30 * * * * ?")
    public void cron()  {
        docService.updateEbookInfo();
    }

}
