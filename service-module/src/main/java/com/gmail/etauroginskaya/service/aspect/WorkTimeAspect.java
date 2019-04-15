package com.gmail.etauroginskaya.service.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Component
public class WorkTimeAspect {
    private static final Logger logger = LogManager.getLogger(WorkTimeAspect.class);
    private LocalDateTime startMethodTime;
    private LocalDateTime endMethodTime;
    private String methodName;

    @Pointcut("execution(* com.gmail.etauroginskaya.service.DocumentService.*(..))")
    public void allMethodsPointcut() {
    }

    @Before("allMethodsPointcut()")
    public void allStartServiceMethodsAdvice(JoinPoint jp) {
        methodName = jp.getSourceLocation().getWithinType().toString().concat("." + jp.getSignature().getName());
        startMethodTime = LocalDateTime.now();
    }

    @After("allMethodsPointcut()")
    public void allEndServiceMethodsAdvice() {
        endMethodTime = LocalDateTime.now();
        String duration = Duration.between(startMethodTime, endMethodTime).toMillis() + " ms";
        logger.info(methodName + "(), start time: " + startMethodTime + " , " +
                "end time: " + endMethodTime + " , method duration: " + duration);
    }


}
