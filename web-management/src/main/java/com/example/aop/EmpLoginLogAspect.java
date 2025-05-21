package com.example.aop;


import com.example.mapper.EmpLoginLogMapper;
import com.example.pojo.Emp;
import com.example.pojo.EmpLoginLog;
import com.example.pojo.LoginInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Aspect
@Component
public class EmpLoginLogAspect {

    @Autowired
    private EmpLoginLogMapper empLoginLogMapper;

    @Around("execution(* com.example.service.Impl.*.login(..))")
    public Object logOperation(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        EmpLoginLog logEntry = new EmpLoginLog();
        Object result = null;
        boolean isSuccess = false;

        try {
            Emp emp = (Emp) joinPoint.getArgs()[0];
            logEntry.setUsername(emp.getUsername());
            logEntry.setPassword(emp.getPassword());
            logEntry.setLoginTime(LocalDateTime.now());
            result = joinPoint.proceed();

            if(result instanceof LoginInfo loginInfo){
                logEntry.setIsSuccess((short)1);
                logEntry.setJwt(loginInfo.getToken());
                isSuccess = true;
            }else {
                logEntry.setIsSuccess((short)0);
            }
            return result;

        } catch (Exception e) {
            logEntry.setIsSuccess((short)0);
            throw e;
        }finally {
            long endTime = System.currentTimeMillis();
            logEntry.setCostTime(endTime - startTime);

            empLoginLogMapper.insert(logEntry);
            log.info("登入日誌紀錄完成: {}" , logEntry);
        }
    }
}
