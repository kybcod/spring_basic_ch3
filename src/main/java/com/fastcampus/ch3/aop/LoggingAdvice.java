package com.fastcampus.ch3.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggingAdvice {
    @Around("execution(* com.fastcampus.ch3.aop.MyMath.*(..))") //pointcut - 부가기능이 적용할 메서드의 패턴
    public Object methodCallLog(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        System.out.println("<<{start}" + joinPoint.getSignature().getName() + Arrays.toString(joinPoint.getArgs()));

        Object result = joinPoint.proceed();
        System.out.println("result = " + result);
        System.out.println("<<{end}" + (System.currentTimeMillis() - start)+"ms");

    }
}
