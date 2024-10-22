package me.dwywdo.labs.java.syntax.package_interface.ex7_method_conflict;

import org.junit.jupiter.api.Test;

public class MultiClassInterfaceMethodConflictTest {
    @Test
    void methodConflictTest() {
        final MultiClassInterface m1 = new MultiClassInterface();

        m1.styleSame();
        // 인터페이스로 업캐스팅하더라도 인스턴스 메서드가 호출된다.

        ((A1) m1).styleSame();

        /*
        만약 인스턴스 메서드가 아닌 인터페이스 쪽의 Default Method를 호출하고 싶다면
        같은 내용으로 MultiClassInterface 에서 오버라이딩해주면 된다.
         */
    }
}
