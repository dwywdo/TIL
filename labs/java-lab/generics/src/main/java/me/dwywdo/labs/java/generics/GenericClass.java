package me.dwywdo.labs.java.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericClass {
    static class A {}
    static class B extends A {}

    public static void main(String[] args) {
        List<B> listB = new ArrayList<B>();
        // List<A> listA = listB; // 대입할 수 없다. 서브타입 관계가 유지되지 않음
        // 근데 Wildcard로 타입 Argument를 넣어주면 가능하다.
        List<? extends A> listA = listB;
        List<? super B> listA2 = listB;
        // 아래 코드도 안된다. 와일드카드를 쓴다고 전부 유연하게 되는 것은 아니다.
        // List<? super A> listA3 = listB;

        // 예를 들면 아래의 코드가 동작하지 않는다.
        // listA.add(new B());
        // 원래 참조에는 넣을 수 있다.
        listB.add(new B());

        // Null만 넣을 수 있다.
        listA.add(null);

        // Wildcard로 정의해야 하는 경우와 Generic으로 정의해야 하는 경우는 어떻게 구분되는가
    }
}
