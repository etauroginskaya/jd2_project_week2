package com.gmail.etauroginskaya.service.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Aspect
@Component
public class WorkTimeAspect {
    private static final Logger logger = LogManager.getLogger(WorkTimeAspect.class);

    @Pointcut("execution(* com.gmail.etauroginskaya.service.DocumentService.*(..))")
    public void allMethodsPointcut() {
    }

    @Around("allMethodsPointcut()")
    public void aroundAllMethod(ProceedingJoinPoint jp) throws Throwable {
        String methodName = jp.getSourceLocation().getWithinType().toString().concat("." + jp.getSignature().getName());
        LocalDateTime startMethodTime = LocalDateTime.now();
        jp.proceed();
        LocalDateTime endMethodTime = LocalDateTime.now();
        String duration = Duration.between(startMethodTime, endMethodTime).toMillis() + " ms";
        logger.info(methodName + "(), start time: " + startMethodTime + " , " +
                "end time: " + endMethodTime + " , method duration: " + duration);
    }
}
