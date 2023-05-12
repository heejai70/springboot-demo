package com.example.springboot_demo.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component // 서버가 시작할때 해당 클래스를 로딩하라
public class LoggingAspect {
    //@Around : 메소드 실행 전과 후 모두 수행
    @Around("execution(* com.example.springboot_demo.controller.UserController.*(..))")
    public Object setLog(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("실행 시작: "
                + pjp.getSignature().getDeclaringTypeName() + "."
                + pjp.getSignature().getName());
        long startMillis = System.currentTimeMillis();
        Object result = pjp.proceed(); // 대상 메소드 실행
        long executionMillis = System.currentTimeMillis() - startMillis;

        System.out.println("실행 완료: " + executionMillis + "밀리초 소요됨"
                + pjp.getSignature().getDeclaringTypeName() + "."
                + pjp.getSignature().getName());
        return result;
    }
    //서비스 패키지내에 모든 get메소드 대상으로 실행
    @Before("execution(* com.example.springboot_demo.service.*.get*(..))")
    public void logger() {
        System.out.println("logger test before service methods");
    }   
}
