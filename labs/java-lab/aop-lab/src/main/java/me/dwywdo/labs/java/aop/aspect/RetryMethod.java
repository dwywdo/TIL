package me.dwywdo.labs.java.aop.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RetryMethod {

    public int retries() default 5;

    public int interval() default 5;

    public int backoff() default 1;

}
