package me.dwywdo.labs.java.generics;

import java.io.Closeable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Generics {
    // Generic Method
    <T> void print(T t) { System.out.println(t.toString()); }

    // Bounded Type Parameter
    public static class Hello<T extends List> {

    }

    // Multiple Bound with Intersection Type
    static <T extends List & Serializable & Comparable & Closeable> void printList(T t) {

    }

    static long countGreaterThan(Integer[] arr, Integer elem) {
        return Arrays.stream(arr).filter(s -> s > elem).count();
    }

    static <T extends Comparable<T>> long genericCountGreaterThan(T[] arr, T elem) {
        return Arrays.stream(arr).filter(s -> s.compareTo(elem) > 0).count();
    }

    // Inheritance
    static class MyList<E, P> implements List<E> {

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<E> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(E e) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends E> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public E get(int index) {
            return null;
        }

        @Override
        public E set(int index, E element) {
            return null;
        }

        @Override
        public void add(int index, E element) {

        }

        @Override
        public E remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<E> listIterator() {
            return null;
        }

        @Override
        public ListIterator<E> listIterator(int index) {
            return null;
        }

        @Override
        public List<E> subList(int fromIndex, int toIndex) {
            return List.of();
        }
    }

    // Type Inference
    static <T> void method(T t, List<T> list) {

    }

    // 아래 두 메서드의 차이는 무엇일까?
    //
    static <T extends Comparable> void noWildcardMethod(List<T> t) {

    }

    //
    static void wildcardMethod(List<? extends Comparable> t) {

    }

    static void printWithoutWildcardList(List<Object> list) {
        list.forEach(s -> System.out.println(s.toString()));
    }

    static void printWildcardList(List<?> list) {
        list.forEach(s -> System.out.println(s.toString()));
    }

    static void printWildcardAndUpperboundList(List<? extends Comparable> t) {
        t.forEach(s -> s.compareTo("A"));
    }


    public static void main(String[] args) {
        // 올바르게 사용하는 예
        final List<String> list = new ArrayList<>();
        // 아래 코드가 컴파일 시에 체크가 된다.
        // list.add(1);

        // 잘못 사용하는 예 (캐스팅을 꼭 필요로 해서 Typesafe하지 않게 된다.)
        final List wrongList = new ArrayList<>();
        wrongList.add("hello");
        String s = (String) wrongList.get(0);
        // Integer i = (Integer) wrongList.get(0); // ClassCastException

        // Raw Type이란?
        // List와 같이 원래 Generic으로 선언이 된 클래스들인데 타입 파라미터에 타입 Argument를 주지 않고 사용되는 경우
        final List rawTypeList = new ArrayList<Integer>();
        // 동작은 한다 + Warning: Raw use of parameterized class 'List'

        List<Integer> ints = Arrays.asList(1, 2 ,3);

        List rawInts = ints;
        // Unchecked Assignment: 'java.util.List' to 'java.util.list<Java.lang.Integer>'
        @SuppressWarnings("unchecked") // Warning을 막을 수 있음
        List<Integer> ints2 = rawInts; // Compile Warning Message = uses unchecked or unsafe operations.

        List<String> str = rawInts; // To Wrong Parameterized Type
        // 아래 코드는 실제로 Integer가 요소로 들어가있으므로 String에 넣으려고 하는 순간 ClassCastException가 발생한다.
        // String string = str.get(0);
        // 컴파일 에러가 발생하지 않는 이유?
        // Java 5 이전의 코드는 List에서는 사용 가능했으니까...!

        new Generics().print("Hello");
        new Generics().print(1);

        // Bounded Type Parameter
        final Integer[] intArr = new Integer[] { 1, 2, 3, 4, 5, 6, 7};
        System.out.println("countGreaterThan(arr, 4) = " + countGreaterThan(intArr, 4));

        final String[] strArr = new String[] {"a", "b", "c", "d", "e", "f"};
        System.out.println("genericCountGreaterThan(strArr, \"c\") = " + genericCountGreaterThan(strArr, "c"));
        System.out.println("genericCountGreaterThan(intArr, 4) = " + genericCountGreaterThan(intArr, 4));

        // Inheritance
        final Integer i = 10;
        Number n = i;

        List<Integer> intList = new ArrayList<>();
        List<Number> numList = new ArrayList<>();

        // numList = intList;

        ArrayList<Integer> arrList = new ArrayList<>();
        List<Integer> intList2 = arrList;

        List<String> s1 = new MyList<String, Integer>();
        List<String> s2 = new MyList<String, String>();

        s1 = s2;
        s2 = s1;

        // Type Inference
        // T Type이 무엇인지 컴파일러가 알아서 체크해준다 (아래 예에서는 Integer)
        method(1, Arrays.asList(1, 2, 3));

        // Java 6 / 7 에서는 많은 경우에 이것을 체크 못하는 경우가 많다.
        // 이 경우 T 타입이 아래 방식으로 무엇이 될지를 결정해서 알려주어야 한다.
        Generics.<Integer>method(1, Arrays.asList(1, 2, 3));

        // Diamond Operator
        final List<String> listWithDiamondOperator = new ArrayList<>();
        // 아래 경우도 Type Inference 가 동작한다.
        final List<String> c = Collections.emptyList();
        final List<String> cForJava67 = Collections.<String>emptyList();

        List<?> wildCardList; // ?: Wildcard. 이 타입을 모른다 -> 알 필요도 없고 관심이 없다.
        printWithoutWildcardList(Arrays.asList(1, 2, 3));
        printWildcardList(Arrays.asList(1, 2, 3));

        // 무슨 차이가 있나?
        // 아래처럼 타입이 명확하게 정해지는 순간 넣어줄 수가 없다.
        // 왜? List<Integer>는 List<Object>의 서브타입이 아니기 때문
        // 그래서 Wildcard를 써야 한다.
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        // printWithoutWildcardList(integerList);
        printWildcardList(integerList);

        // Wildcard와 Upperbound를 섞어서 쓸 수도 있다.
        printWildcardAndUpperboundList(integerList);
    }
}
