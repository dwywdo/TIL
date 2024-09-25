package me.dwywdo.labs.java.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Wildcard {
    // Generic Method?
    // Method의 타입 파라미터는 Return 타입 앞에 명시된다.
    // 타입 파라미터가 메서드 레벨에 붙기 때문에, Static 메서드에도 사용할 수 있다.
    // Type Parameter를 정의한 경우 메서드 내 인스턴스 변수를 해당 타입으로 정의할 수 있다.
    static <T> void genericMethod(List<T> list) { }

    // Wildcard를 사용해 정의.
    // Wildcard로 정의하는 경우 뭔가의 타입을 가져다가 메서드 내부에서 사용하지 않는다.
    // 그럼 이걸 왜 쓰느냐?
    // 제약: list.add()에 구체적인 값을 넣을 수 없고, null만 넣을 수 있다.
    // 찾아보니 null도 넣을 수 없고 clear()도 호출 불가능
    // 그 외에 특정 타입을 알아야만 수행할 수 있는 Operation은 호출할 수 없다.
    static void wildcardMethod(List<?> list) {
        // list.add(null);
        // list.clear();
        System.out.println("list.size() = " + list.size());
        Iterator<?> it = list.iterator();
        list.toString();
        list.equals(null);
    }

    public static void main(String[] args) {
        // 위의 두 메서드는 유사하게 사용될 수 있지만, 차이가 있다.
        // 기능을 구현하는 관점에서는 차이가 없을 수 있다.
        // 용도와 관례, 기능적인 제한이 좀 다르다.

        final List<Integer> list = Arrays.asList(1, 2, 3, 3, 5, 4, 3, 1);
        genericMethod(list);
        wildcardMethod(list);
        // List<Integer>가 List<Object>의 서브타입이 아니므로 아래 코드는 실행 불가능
        // normalMethod(list);

        System.out.println("isEmpty(list); = " + isEmpty(list));
        System.out.println("isEmptyWithWildcard(list) = " + isEmptyWithWildcard(list));

        System.out.println("frequency(list, 3) = " + frequency(list, 3));
        System.out.println("frequencyWithWildcard(list, 3) = " + frequencyWithWildcard(list, 3));

        System.out.println("max(list) = " + max(list));
        System.out.println("maxWithWildcard(list) = " + maxWithWildcard(list));

        System.out.println("Collections.max(list) = " + Collections.max(list));
        System.out.println("Collections.max(list, (a,b) -> a - b) = " + Collections.max(list, (a, b) -> a - b));
        System.out.println("Collections.max(list, (Comparator<Integer>)(a, b) -> a - b) = " + Collections.max(list, (Comparator<Integer>)(a, b) -> a - b));
        System.out.println("Collections.max(list, (Comparator<Object>)(a, b) -> a.toString().compareTo(b.toString())) = " + Collections.max(list, (Comparator<Object>)(a, b) -> a.toString().compareTo(b.toString())));

        // Wildcard Capture
        System.out.println("list = " + list);
        reverse(list);
        System.out.println("reversed list = " + list);
        System.out.println("list = " + list);
        reverseWithWildcard(list);
        System.out.println("reversed list = " + list);
    }

    private static <T extends Comparable> T max(List<T> list) {
        return list.stream().reduce((a,b) -> a.compareTo(b) > 0 ? a : b).get();
    }

    private static <T extends Comparable<? super T>> T maxWithWildcard(List<? extends T> list) {
        return list.stream().reduce((a,b) -> a.compareTo(b) > 0 ? a : b).get();
    }

    static <T> long frequency(List<T> list, T elem) {
        return list.stream().filter(s -> s.equals(elem)).count();
    }

    static long frequencyWithWildcard(List<?> list, Object elem) {
        return list.stream().filter(s -> s.equals(elem)).count();
    }

    // 아래 두 메서드는 전혀 차이가 없다.
    static <T> boolean isEmpty(List<T> list) {
        return list.size() == 0;
    }

    static boolean isEmptyWithWildcard(List<?> list) {
        return list.size() == 0;
    }

    // T Type 정보가 의미있게 사용되고 있는가?
    private static <T> void reverse(List<T> list) {
        final List<T> temp = new ArrayList<>(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, temp.get(list.size() - i - 1));
        }
    }

    private static void reverseWithWildcard(List<?> list) {
        // 대입이 가능하다는 이야기는 List<?> 와 List<T>가 일치하거나 Super 타입이어야 하는데...?
        // 실제로는 그렇지 않지만 문제가 없이 코드가 넘어간다. 왜?
        // 원래는 전환이 안되는데... Wildcard가 적용된 리스트를 Generic Type Parameter가 적용된 타입으로 Capture를 해준다.
        reverseHelper(list);
    }

    private static <T> void reverseHelper(List<T> list) {
        final List<T> temp = new ArrayList<>(list);
        for (int i = 0; i < list.size(); i++) {
            list.set(i, temp.get(list.size() - i - 1)); // Compile Error
        }
    }
}
