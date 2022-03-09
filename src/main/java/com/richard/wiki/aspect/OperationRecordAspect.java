package com.richard.wiki.aspect;

import com.richard.wiki.annotation.OperationRecord;
import com.richard.wiki.common.CommonConstant;
import com.richard.wiki.domain.Record;
import com.richard.wiki.domain.UserInfo;
import com.richard.wiki.mapper.RecordMapper;
import com.richard.wiki.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class OperationRecordAspect {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private RecordMapper recordMapper;

    @Pointcut("@annotation(com.richard.wiki.annotation.OperationRecord)")
    public void operationRecord() {}

    @AfterReturning(value = "operationRecord()")
    public void saveOperationLog(JoinPoint joinPoint) {
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            OperationRecord annotation = method.getAnnotation(OperationRecord.class);
            // 获取当前用户信息
            UserInfo userInfo = (UserInfo) httpServletRequest.getAttribute(CommonConstant.LOGIN_USER_INFO);
            // 当前时间
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateStr = formatter.format(new Date());
            // 组装操作记录数据
            Record record = new Record();
            record.setRecordId(userInfo.getUserId());
            record.setType(Integer.valueOf(annotation.type()));
            record.setDesc(annotation.desc());
            record.setRecordTime(dateStr);
            record.setOperaUrl(httpServletRequest.getRequestURI());
            record.setMethodName(method.getName());
            record.setServiceName(method.getDeclaringClass().getName());
            recordMapper.insert(record);

        }catch (Exception e) {
            e.printStackTrace();
            LOG.info(e.getMessage(),e);
        }
    }

}
