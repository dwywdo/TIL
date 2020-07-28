package org.springframework.samples.petclinic.owner;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // 어디에 쓸 수 있는지 -> 메소드에다 쓰겠다
@Retention(RetentionPolicy.RUNTIME) // 얼마동안 유지할 것인지
public @interface LogExecutionTime {

}
