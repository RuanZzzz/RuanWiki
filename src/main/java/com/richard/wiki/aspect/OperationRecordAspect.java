package com.richard.wiki.aspect;

import com.richard.wiki.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class OperationRecordAspect {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Pointcut("@annotation(com.richard.wiki.annotation.OperationRecord)")
    public void operationRecord() {}

    @AfterReturning(value = "operationRecord()")
    public void saveOperationLog(JoinPoint joinPoint) {
        try {

        }catch (Exception e) {
            e.printStackTrace();
            LOG.info(e.getMessage(),e);
        }
    }

}
