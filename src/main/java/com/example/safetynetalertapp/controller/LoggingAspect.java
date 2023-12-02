package com.example.safetynetalertapp.controller;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @AfterReturning(pointcut = "execution(* com.example.safetynetalertapp..*(..))", returning = "result")

    public void logAfterReturning(JoinPoint joinPoint, Object result) {

        String className = joinPoint.getTarget().getClass().getName();

        String methodName = joinPoint.getSignature().getName();

        logger.info("{}.{} returned: {}", className, methodName, result);
    }
}