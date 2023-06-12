package dev.isaac.springbootrestapp.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect // 컴포넌트와 함께 해서 Aspect로 setting
@Component // server 로드 시 컴포넌트가 같이 세팅되어 적용된다.
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    // point-cut  : spring-aop LogControoler 전체 메소드 단위에 setLog 를 실행함.
    // point-cut + advice 를 aspect 라고 한다.
    @Around("execution(* dev.isaac.springbootrestapp.controller.LogController.*(..))")
    // advice
    public Object setLog(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("start : " + pjp.getSignature().getDeclaringTypeName() + "/" + pjp.getSignature().getName());

        System.out.println("실행 시작: " + pjp.getSignature().getDeclaringTypeName() + "." + pjp.getSignature().getName());

        long startMillis = System.currentTimeMillis();
        Object result = pjp.proceed();
        long executionMillis = System.currentTimeMillis() - startMillis;

        System.out.println("실행 완료: " + executionMillis + "밀리초 소요됨 :"
                + pjp.getSignature().getDeclaringTypeName() + "."
                + pjp.getSignature().getName());

        logger.info("finished : " + pjp.getSignature().getDeclaringTypeName() + "/" + pjp.getSignature().getName());
        return result;  // return하면 Around Aspect가 실행된다.
    }

    // Aspect : methods 단위로 지정, naming 컨벤션으로 create으로 시작하는 모든 메소드를 지정
    @Before("execution(* dev.isaac.springbootrestapp.service.JwtService.create*(..))")  // point-cut
    public void logger() {
        System.out.println("[before test] create request...");
    }


}
