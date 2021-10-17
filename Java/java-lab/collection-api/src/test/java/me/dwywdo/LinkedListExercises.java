package me.dwywdo;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * https://www.w3resource.com/java-exercises/collection/index.php#linkedlist
 */
public class LinkedListExercises {
    /**
     * Write a Java program to append the specified element to the end of a linked list.
     * Use add() method to add values in the linked list
     */
    @Test
    public void exercise1() {
        List<String> linkedList = new LinkedList<String>();

        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");
        linkedList.add("Yellow");

        System.out.println("The linked list: " + linkedList);
    }

    /**
     * Write a Java program to iterate through all elements in a linked list.
     */
    @Test
    public void exercise2() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");
        linkedList.add("Yellow");

        for (String element : linkedList) {
            System.out.println(element);
        }
    }

    /**
     * Write a Java program to iterate through all elements in a linked list starting at the specified position.
     */

    @Test
    void exercise3() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");

        Iterator<String> p = linkedList.listIterator(1);
        while (p.hasNext()) {
            System.out.println(p.next());
        }
    }

    /**
     * Write a Java program to iterate a linked list in reverse order.
     */
    @Test
    void exercise4() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");

        Iterator it = linkedList.descendingIterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * Write a Java program to insert the specified element at the specified position in the linked list.
     * Write a Java program to insert elements into the linked list at the first and last position.
     */
    @Test
    void exercise5_6() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");

        linkedList.addFirst("firstColor");
        linkedList.add(1, "insertedColor");
        linkedList.addLast("lastColor");

        System.out.println("Linked List: " + linkedList);
    }

    /**
     * Write a Java program to insert the specified element at the front of a linked list.
     * Write a Java program to insert the specified element at the end of a linked list.
     */
    @Test
    void exercise7_8() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");

        /**
         * offer*() 와 add*()의 차이?
         * 메소드 실행이 불가할 때 무엇을 Return하느냐의 차이
         * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Queue.html
         * add()의 경우 더 이상 요소를 삽입할 수 없을 때에 Unchecked Exception을 던지게 된다.
         * offer()의 경우 동일한 케이스에 대해서는 false를 리턴한다.
         * 길이가 고정되어 있는 리스트에서, 더 이상 삽입할 수 없는 경우를 정상적인 경우로 고려하고 싶을 때에
         * offer()를 사용해서 처리하면 되는 것으로 보인다.
         */
        linkedList.offerFirst("offeredFirstColor");
        linkedList.offerLast("offeredLastColor");

        System.out.println("Linked List: " + linkedList);
    }

    /**
     * Write a Java program to insert some elements at the specified position into a linked list.
     */
    @Test
    void exercise9() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");

        LinkedList<String> newLinkedList = new LinkedList<>();
        newLinkedList.add("newColor1");
        newLinkedList.add("newColor2");

        linkedList.addAll(3, newLinkedList);

        System.out.println(linkedList);
    }

    /**
     * Write a Java program to get the first and last occurrence of the specified elements in a linked list.
     */
    @Test
    void exercise10() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");

        Object firstElement = linkedList.getFirst();
        System.out.println("First Element is " + firstElement);

        Object lastElement = linkedList.getLast();
        System.out.println("Last Element is " + lastElement);
    }

    /**
     * Write a Java program to display the elements and their positions in a linked list.
     */
    @Test
    void exercise11() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");

        for (int p = 0; p < linkedList.size(); p++) {
            System.out.println("Element at index " + p + ": " + linkedList.get(p));
        }
    }

    /**
     * Write a Java program to remove a specified element from a linked list.
     * Write a Java program to remove first and last element from a linked list.
     */
    @Test
    void exercise12_13() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");

        System.out.println(linkedList);
        linkedList.remove(3);
        linkedList.removeFirst();
        linkedList.removeLast();
        System.out.println(linkedList);
    }

    /**
     * Write a Java program to remove all the elements from a linked list.
     */
    @Test
    void exercise14() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");

        System.out.println(linkedList);
        linkedList.clear();
        System.out.println(linkedList);
    }

    /**
     * Write a Java program of swap two elements in a linked list.
     */
    @Test
    void exercise15() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");

        System.out.println(linkedList);
        Collections.swap(linkedList,0, 2);
        System.out.println(linkedList);
    }

    /**
     * Write a Java program to shuffle the elements in a linked list.
     */
    @Test
    void exercise16() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");

        System.out.println(linkedList);
        Collections.shuffle(linkedList);
        System.out.println(linkedList);
    }

    /**
     * Write a Java program to join two linked lists.
     */
    @Test
    void exercise17() {
        LinkedList<String> linkedList1 = new LinkedList<>();
        linkedList1.add("Red1");
        linkedList1.add("Green1");
        linkedList1.add("Black1");
        linkedList1.add("White1");
        linkedList1.add("Pink1");

        LinkedList<String> linkedList2 = new LinkedList<>();
        linkedList2.add("Red2");
        linkedList2.add("Green2");
        linkedList2.add("Black2");
        linkedList2.add("White2");
        linkedList2.add("Pink2");

        LinkedList<String> joinedList = new LinkedList<>();
        joinedList.addAll(linkedList1);
        joinedList.addAll(linkedList2);
        System.out.println(joinedList);
    }

    /**
     * Write a Java program to clone an linked list to another linked list.
     */
    @Test
    void exercise18() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");

        LinkedList<String> clonedList = new LinkedList<>();
        clonedList = (LinkedList) linkedList.clone();
        System.out.println(clonedList);
    }

    /**
     * Write a Java program to remove and return the first element of a linked list.
     * Write a Java program to retrieve but does not remove, the first element of a linked list.
     */
    @Test
    void exercise19_20() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");

        System.out.println("Linked List: " + linkedList);
        System.out.println("Removed Element: " + linkedList.pop());
        System.out.println("First Element in the list: " + linkedList.peekFirst());
        System.out.println("Linked List: " + linkedList);
    }

    /**
     * Write a Java program to check if a particular element exists in a linked list.
     */
    @Test
    void exercise22() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");

        if (linkedList.contains("Red")) {
            System.out.println("Color Red is present");
        } else {
            System.out.println("Color Red is not present");
        }

        if (linkedList.contains("Orange")) {
            System.out.println("Color Orange is present");
        } else {
            System.out.println("Color Orange is not present");
        }
    }

    /**
     * Write a Java program to convert a linked list to array list.
     */
    @Test
    void exercise23() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");

        List<String> list = new ArrayList<String>(linkedList);

        for (String str : list) {
            System.out.println(str);
        }
    }

    /**
     * Write a Java program to compare two linked lists.
     */
    @Test
    void exercise24() {
        LinkedList<String> linkedList1 = new LinkedList<>();
        linkedList1.add("Red");
        linkedList1.add("Green");
        linkedList1.add("Black");
        linkedList1.add("White");
        linkedList1.add("Pink");

        LinkedList<String> linkedList2 = new LinkedList<>();
        linkedList2.add("Red");
        linkedList2.add("Green");
        linkedList2.add("Black");
        linkedList2.add("White");
        linkedList2.add("Pink");

        LinkedList<String> linkedList3 = new LinkedList<>();
        for (String s : linkedList1) {
            linkedList3.add(linkedList2.contains(s) ? "Yes" : "No");
        }
        System.out.println(linkedList3);
    }

    /**
     * Write a Java program to test a linked list is empty or not.
     */
    @Test
    void exercise25() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");
        System.out.println("Check the above linked list is empty or not! " + linkedList.isEmpty());
        linkedList.removeAll(linkedList);
        System.out.println("Check the above linked list is empty or not! " + linkedList.isEmpty());
    }

    /**
     * Write a Java program to replace an element in a linked list.
     */
    @Test
    void exercise26() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Red");
        linkedList.add("Green");
        linkedList.add("Black");
        linkedList.add("White");
        linkedList.add("Pink");
        System.out.println(linkedList);
        linkedList.set(1, "Orange");
        System.out.println(linkedList);
    }
}

