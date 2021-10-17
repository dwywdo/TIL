package me.dwywdo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * https://www.w3resource.com/java-exercises/collection/index.php#arraylist
 * https://www.codejava.net/java-core/collections/18-java-collections-and-generics-best-practices
 * https://www.javaguides.net/2018/06/java-collection-framework-best-practices.html
 */
public class ArrayListExercises {
    /**
     * Write a Java program to create a new array list,
     * add some elements (string) and print out the collection.
     */
    @Test
    public void exercise1() {
        List<String> arrayListOfStrings = new ArrayList<String>();
        arrayListOfStrings.add("Red");
        arrayListOfStrings.add("Green");
        arrayListOfStrings.add("Orange");
        arrayListOfStrings.add("White");
        arrayListOfStrings.add("Black");
        System.out.println(arrayListOfStrings);
    }

    /**
     * Write a Java program to iterate through all elements in an array list.
     */
    @Test
    public void exercise2() {
        List<String> arrayListOfStrings = Arrays.asList("one", "two", "three", "four", "five");
        for (String number: arrayListOfStrings) {
            System.out.println(number);
        }
    }

    /**
     * Write a Java program to insert an element into the array list at the first position.
     */
    @Test
    public void exercise3() {
        // Arrays.asList()는 Fixed-length의 ArrayList를 반환한다..!
        List<String> fixedLengthArrayList = Arrays.asList("one", "two", "three");
        List<String> arrayListOfStrings = new ArrayList<>();
        arrayListOfStrings.add("Red");
        arrayListOfStrings.add("Green");
        arrayListOfStrings.add("Orange");
        arrayListOfStrings.add("White");
        arrayListOfStrings.add("Black");
        arrayListOfStrings.add(0, "Pink");
        System.out.println(arrayListOfStrings);
    }

    /**
     * Write a Java program to retrieve an element (at a specified index) from a given array list.
     */
    @Test
    public void exercise4() {
        List<String> fixedLengthArrayList = Arrays.asList("one", "two", "three");
        String firstElement = fixedLengthArrayList.get(0);
        String lastElement = fixedLengthArrayList.get(fixedLengthArrayList.size()-1);
        System.out.println("First Element: " + firstElement);
        System.out.println("Last Element: " + lastElement);
    }

    /**
     * Write a Java program to update specific array element by given element.
     */
    @Test
    public void exercise5() {
        List<String> fixedLengthArrayList = Arrays.asList("one", "two", "three");
        fixedLengthArrayList.set(1, "TWO");
        System.out.println(fixedLengthArrayList);
    }

    /**
     * Write a Java program to remove the third element from an array list.
     */
    @Test
    public void exercise6() {
        List<String> fixedLengthArrayList = Arrays.asList("one", "two", "three"); // 마찬가지로 안된다.
        List<String> arrayListOfString = new ArrayList<>();
        arrayListOfString.add("one");
        arrayListOfString.add("two");
        arrayListOfString.add("three");
        arrayListOfString.remove(0);
        System.out.println(arrayListOfString);
    }

    /**
     * Write a Java program to search an element in an array list.
     */
    @Test
    public void exercise7() {
        List<String> fixedLengthArrayList = Arrays.asList("one", "two", "three");
        if (fixedLengthArrayList.contains("one")) {
            System.out.println("Found element: one");
        }
        else {
            System.out.println("There is no such element: one");
        }
    }

    /**
     * Write a Java program to sort a given array list.
     */
    @Test
    public void exercise8() {
        List<String> fixedLengthArrayList = Arrays.asList("Red", "Green", "Orange", "White", "Black");
        System.out.println(fixedLengthArrayList);
        Collections.sort(fixedLengthArrayList);
        System.out.println(fixedLengthArrayList);
    }

    /**
     * Write a Java program to copy one array list into another.
     */
    @Test
    public void exercise9() {
        List<String> arrayListOfString1 = new ArrayList<>();
        arrayListOfString1.add("1");
        arrayListOfString1.add("2");
        arrayListOfString1.add("3");
        arrayListOfString1.add("4");
        System.out.println(arrayListOfString1);

        List<String> arrayListOfString2 = new ArrayList<>();
        arrayListOfString2.add("A");
        arrayListOfString2.add("B");
        arrayListOfString2.add("C");
        arrayListOfString2.add("D");
        System.out.println(arrayListOfString2);

        // Destination의 길이가 Source의 길이보다 길거나 같아야 한다.
        Collections.copy(arrayListOfString2, arrayListOfString1);

        System.out.println(arrayListOfString1);
        System.out.println(arrayListOfString2);
    }

    /**
     * Write a Java program to shuffle elements in a array list.
     */
    @Test
    public void exercise10() {
        List<String> arrayListOfString = Arrays.asList("Red", "Green", "Orange", "White", "Black");

        System.out.println(arrayListOfString);

        Collections.shuffle(arrayListOfString);

        System.out.println(arrayListOfString);
    }

    /**
     * Write a Java program to reverse elements in a array list.
     */
    @Test
    public void exercise11() {
        List<String> arrayListOfString = Arrays.asList("Red", "Green", "Orange", "White", "Black");

        System.out.println(arrayListOfString);

        Collections.reverse(arrayListOfString);

        System.out.println(arrayListOfString);
    }

    /**
     * Write a Java program to extract a portion of a array list.
     */
    @Test
    public void exercise12() {
        List<String> arrayListOfString = Arrays.asList("Red", "Green", "Orange", "White", "Black");

        System.out.println(arrayListOfString);

        List<String> subList = arrayListOfString.subList(0, 3);

        System.out.println(subList);

    }

    /**
     * Write a Java program to reverse elements in a array list.
     */
    @Test
    public void exercise13() {
        List<String> a1 = new ArrayList<>();
        a1.add("Red");
        a1.add("Green");
        a1.add("Black");
        a1.add("White");
        a1.add("Pink");

        List<String> a2 = new ArrayList<>();
        a2.add("Red");
        a2.add("Green");
        a2.add("Black");
        a2.add("Pink");

        List<String> a3 = new ArrayList<>();
        for (String e: a1) {
            a3.add(a2.contains(e) ? "Yes" : "No");
        }
        System.out.println(a3);

    }

    /**
     * Write a Java program of swap two elements in an array list.
     */
    @Test
    public void exercise14() {
        ArrayList<String> a1 = new ArrayList<>();
        a1.add("Red");
        a1.add("Green");
        a1.add("Black");
        a1.add("White");
        a1.add("Pink");

        System.out.println(a1);

        Collections.swap(a1, 0, 2);

        System.out.println(a1);
    }

    /**
     * Write a Java program to join two array lists.
     */
    @Test
    public void exercise15() {
        List<String> a = Arrays.asList("Red", "Green" , "Black", "White", "Pink");
        List<String> b = Arrays.asList("Red", "Green" , "Black", "Pink");

        ArrayList<String> c = new ArrayList<>();
        c.addAll(a);
        c.addAll(b);

        System.out.println(c);
    }

    /**
     * Write a Java program to clone an array list to another array list.
     */
    @Test
    public void exercise16() {
        ArrayList<String> a = new ArrayList<String>();
        a.add("Red");
        a.add("Green");
        a.add("Black");
        a.add("White");
        a.add("Pink");

        System.out.println(a);

        ArrayList<String> b = (ArrayList<String>)a.clone();

        System.out.println(b);
    }

    /**
     * Write a Java program to empty an array list.
     * Write a Java program to test an array list is empty or not.
     */
    @Test
    public void exercise17() {
        ArrayList<String> a = new ArrayList<String>();
        a.add("Red");
        a.add("Green");

        System.out.println(a);
        System.out.println(a.isEmpty());

        a.removeAll(a);

        System.out.println(a);
        System.out.println(a.isEmpty());
    }

    /**
     * Write a Java program to trim the capacity of an array list the current list size.
     */
    @Test
    public void exercise19() {
        ArrayList<String> a = new ArrayList<>();
        a.add("Red");
        a.add("Green");
        a.add("Black");
        a.add("White");
        a.add("Pink");

        System.out.println(a);

        a.trimToSize();

        System.out.println(a);
    }

    /**
     * Write a Java program to increase the size of an array list.
     */
    @Test
    public void exercise20() {
        ArrayList<String> a = new ArrayList<>(3);
        a.add("Red");
        a.add("Green");
        a.add("Black");

        a.add("White");

        System.out.println(a);

        a.ensureCapacity(6);

        System.out.println(a);

        a.add("Pink");
        a.add("Yellow");

        System.out.println(a);
    }

    /**
     * Write a Java program to replace the second element of a ArrayList with the specified element.
     */
    @Test
    public void exercise21() {
        ArrayList<String> colors = new ArrayList<>();

        colors.add("Red");
        colors.add("Green");

        System.out.println(colors);

        String newColor = "White";
        colors.set(1, newColor);

        System.out.println(colors);
    }

    /**
     * Write a Java program to print all the elements of a ArrayList using the position of the elements.
     */
    @Test
    public void exercise22() {
        ArrayList<String> a = new ArrayList<>();
        a.add("Red");
        a.add("Green");
        a.add("Black");
        a.add("White");
        a.add("Pink");

        System.out.println(a);
        for (int index = 0; index < a.size(); index++) {
            System.out.println(a.get(index));
        }
     }
}
