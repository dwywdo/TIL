package me.dwywdo.labs.java.syntax.package_lambda_expression;

import java.util.function.Consumer;

public class LambdaScopeTest {
    public int x = 0;

    class FirstLevel {
        public int x = 1;
        void methodInFirstLevel(int x) {
            int z = 2;
            // `z`는 값이 변경될 수 없다 (람다식 내에서 사용되려면)
            Consumer<Integer> myConsumer = (y) -> {
                // 이 내부에서도 z의 값을 변경할 수 없다.
                // z라는 이름의 새로운 변수를 선언할 수 없다. (같은 Scope이다)
                System.out.println("x = " + x);
                System.out.println("y = " + y);
                System.out.println("z = " + z);
                System.out.println("this.x = " + this.x);
                System.out.println("LambdaScopeTest.this.x = " + LambdaScopeTest.this.x);
            };

            myConsumer.accept(x);
        }
    }

    public static void main(String[] args) {
        LambdaScopeTest test = new LambdaScopeTest();
        FirstLevel fl = test.new FirstLevel();
        fl.methodInFirstLevel(23);
    }
}
