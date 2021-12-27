package me.dwywdo.lambdas;

import org.junit.jupiter.api.Test;

public class LambdaTest {
    @Test
    void localVariables() {
        int portNumber = 1337;
        // Compile Error. 인스턴스 변수가 아닌 로컬 변수를 람다에서 참조하려면 그 변수는 단 한번만 Assign됨이 보장되어야 한다 (Final OR Effectively Final)
        // Runnable r = () -> System.out.println(portNumber);
        portNumber = 5000;
    }
}
