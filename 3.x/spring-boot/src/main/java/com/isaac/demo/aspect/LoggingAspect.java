package com.isaac.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Before("execution(* com.isaac.demo.restservice.*.greeting*(..))")
    public void loggerBefore() {
        log.info("greeting 메서드가 시작됩니다.");
    }

    @After("execution(* com.isaac.demo.restservice.*.greeting*(..))")
    public void loggerAfter() {
        log.info("greeting 메서드가 종료되었습니다.");
    }

    @Around("execution(* com.isaac.demo.restservice.GreetingController.*(..))")
    public void loggerAround(ProceedingJoinPoint pjp) throws Throwable {
        long beforeTimeMillis = System.currentTimeMillis();

        log.info("실행시작 : {}.{}",
                pjp.getSignature().getDeclaringType(),
                pjp.getSignature().getName());
        Object result = pjp.proceed();

        long afterTimeMillis = System.currentTimeMillis() - beforeTimeMillis;
        log.info("실행완료. {} 초 소요 : {}.{}",
                afterTimeMillis,
                pjp.getSignature().getDeclaringType(),
                pjp.getSignature().getName());
    }


}
