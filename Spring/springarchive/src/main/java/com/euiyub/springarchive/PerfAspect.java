package com.euiyub.springarchive;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerfAspect {
    /**
     * We need 2 information
     * 1. What to do (Advice)
     * 2. Where to apply (PointCut)
     */
    // Pointcut Expression
    // package - Classes - Methods
    @Around("execution(* com.euiyub..*.EventService.*(..))") // Pointcut, or customized Pointcut
    public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
        // pjp: createEvent, publishEvent method themselves
        final long begin = System.currentTimeMillis();

        final Object retVal = pjp.proceed();

        System.out.println(System.currentTimeMillis() - begin);
        return retVal;

    }
}
