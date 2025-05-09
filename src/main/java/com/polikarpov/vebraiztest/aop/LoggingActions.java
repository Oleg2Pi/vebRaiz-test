package com.polikarpov.vebraiztest.aop;

import com.polikarpov.vebraiztest.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingActions {

    @Before(value = "execution(public * com.polikarpov.vebraiztest.controller.*Controller..*(..))")
    public void addLoggingBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();

        log.info("Method: {}, args: {}", methodName, Arrays.toString(args));
    }

    @AfterReturning(value = "execution(public * com.polikarpov.vebraiztest.controller.*Controller..*(..))",
    returning = "result")
    public void addLoggingAfter(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();

        log.info("Method: {}, result: {}", methodName, result.toString());
    }

}
