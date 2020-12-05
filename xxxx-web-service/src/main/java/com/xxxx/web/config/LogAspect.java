package com.xxxx.web.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * @Author niemengjin
 * @Description aop日志，记录请求参数和service方法运行总时间
 * @Date 2019/1/24 2:31 PM
 **/
@Slf4j
@Aspect
@Component
public class LogAspect {


    ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    @Pointcut("execution(public * com.xxxx.web.controller.*.*(..))")
    public void webLog() {

    }

    @Pointcut("execution(public * com.xxxx.web.service.*.*(..))")
    public void serviceLog() {

    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("request url:{}", request.getServletPath());
        log.info("request args:{}", Arrays.toString(joinPoint.getArgs()));

    }

    @Before("serviceLog()")
    public void serviceBefore() {
        threadLocal.set(System.currentTimeMillis());

    }

    @AfterReturning(pointcut = "serviceLog()")
    public void doAfterReturning(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        log.info("方法({})执行总时间:{}ms", methodName, System.currentTimeMillis() - threadLocal.get());
    }

}