package me.dwywdo.labs.java.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import me.dwywdo.labs.java.exception.BadRequestException;

@Aspect
public class RetryMethodAspect {

    public static final Logger log = LoggerFactory.getLogger(RetryMethodAspect.class);

    @Pointcut("@annotation(RetryMethod) && execution(* *(..))")
    public void retriableMethod() {

    }

    @Around("retriableMethod()")
    public Object retryMethodAdvice(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("Advice called for annotation RetryMethod");
        log.info(joinPoint.toString());

        final String methodName = ((MethodSignature) joinPoint.getSignature()).getName();
        final int retries = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(
                RetryMethod.class).retries();
        final int interval = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(
                RetryMethod.class).interval();
        final int backoff = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(
                RetryMethod.class).backoff();

        int delay = interval;
        int attempts = 0;

        Object proceed;
        while (attempts < retries) {
            log.info("{} attempt - {}", methodName, attempts);
            attempts++;
            try {
                proceed = joinPoint.proceed();
                return proceed;
            } catch (Exception exception) {
                log.info("Exception at attempt - {}", attempts - 1);
                if (attempts >= retries) {
                    log.info("Max retry attempts reached. Throwing exception.");
                    throw exception;
                }
                if (exception.getClass() == BadRequestException.class) {
                    if (exception.getMessage() != null && exception.getMessage().contains(
                            "Bad Request Received")) {
                        log.info("Sleeping {} seconds before retry.", delay);
                        TimeUnit.SECONDS.sleep(delay);
                        delay *= backoff;
                    }
                } else {
                    throw exception;
                }
            }
        }
        throw new Exception("Failure executing method."); //ideally we never reach this line
    }

}
