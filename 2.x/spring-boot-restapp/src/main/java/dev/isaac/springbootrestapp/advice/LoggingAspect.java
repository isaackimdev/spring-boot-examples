package dev.isaac.springbootrestapp.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* dev.isaac.springbootrestapp.controller.LogController.*(..))")
    public Object setLog(ProceedingJoinPoint pjp) throws Throwable {
        long startMillis = System.currentTimeMillis();
        Object result = pjp.proceed();
        long executionMillis = System.currentTimeMillis() - startMillis;
        System.out.println("실행 완료: " + executionMillis + "밀리초 소요됨 :"
                + pjp.getSignature().getDeclaringTypeName() + "."
                + pjp.getSignature().getName());

        return result;
    }
}
