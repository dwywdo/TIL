package me.dwywdo.junit5practice;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Target(ElementType.METHOD) // 애노테이션 적용 대상: 메소드
@Retention(RetentionPolicy.RUNTIME) // Retention Strategy: 이 애노테이션을 사용한 코드가 애노테이션 정보를 런타임까지 유지해야 한다.
@Test // Jupiter가 제공하는 애노테이션
@Tag("fast")
public @interface FastTest {
}
