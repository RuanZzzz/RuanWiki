//package com.richard.wiki.job;
//
//import com.richard.wiki.interceptor.LoginInterceptor;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//@Component
//public class TestJob {
//
//    private static final Logger LOG = LoggerFactory.getLogger(LoginInterceptor.class);
//
//    /**
//     * 固定时间间隔，fixedRate单位毫秒
//     * @throws InterruptedException
//     */
//    @Scheduled(fixedRate = 1000)
//    public void simple() throws InterruptedException {
//        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss");
//        String dateString = formatter.format(new Date());
//        Thread.sleep(2000);
//        LOG.info("每隔5秒执行一次：{}",dateString);
//    }
//
//    /**
//     * 自定义cron表达式执行
//     * 只有等上一次执行完成，下一次才会在下一个时间点执行
//     * @throws InterruptedException
//     */
//    @Scheduled(cron = "*/1 * * * * ?")
//    public void cron() throws InterruptedException {
//        SimpleDateFormat formatter = new SimpleDateFormat("mm:ss SSS");
//        String dateString = formatter.format(new Date());
//        Thread.sleep(1500);
//        LOG.info("每隔1秒执行一次：{}",dateString);
//    }
//
//}
