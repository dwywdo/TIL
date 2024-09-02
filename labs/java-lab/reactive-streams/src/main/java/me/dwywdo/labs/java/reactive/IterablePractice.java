package me.dwywdo.labs.java.reactive;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public final class IterablePractice {
    // Iterable <---> Observable (Duality)
    public static void main(String[] args) {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        // List로 받을 수도 있지만... Iterable로 받을 수도 있다!
        final Iterable<Integer> iter = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Iterable의 설명을 보면 특이한 게 있다.
        /**
         * Implementing this interface allows an object to be the target of the enhanced
         * {@code for} statement (sometimes called the "for-each loop" statement).
         */

        // Java의 For-each Loop은 아래의 표현식을 말하는 것이다. 이 안에는 Iterable을 쓸 수 있다.
        // for (Integer i : iter) {
        //     System.out.println("i = " + i);
        // }

        // Collection이 아니더래도, 이 Iterable 인터페이스를 구현하면 For-each Loop 내에 사용할 수 있다.
        // 문제 1. 우리는 List 내에 일일이 모든 요소를 적기 싫다... 원소를 모두 쓰지 않고 1~10까지를 출력할 수 있는 코드를 작성해보자.

        /**
         * final Iterable<Integer> iterImpl = new Iterable<>() {
         *             @Override
         *             public Iterator<Integer> iterator() {
         *                 return null;
         *             }
         * };
         */

        // Interface의 구현할 메서드가 하나라면 Lambda로 쓸 수 있다.
        final Iterable<Integer> iterImpl = () -> new Iterator<>() {
            int i = 0;
            static final int MAX = 10;

            @Override
            public boolean hasNext() {
                return i < MAX;
            }

            @Override
            public Integer next() {
                return ++i;
            }
        };

        // 이걸 For-each Loop에 사용해보자. 여러번 사용해도 된다.
        for (Integer i: iterImpl) {
            System.out.println("i = " + i);
        }

        // Java 5 이전에는...? 그나마 세련된 방법
        for (final Iterator<Integer> it = iterImpl.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }
    }
}
