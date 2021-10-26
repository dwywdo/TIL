package me.dwywdo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;

public class HashSetExercises {
    @Test
    void exercise1() {
        HashSet<String> hashSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        System.out.println(hashSet);
        hashSet.add("d");
        System.out.println(hashSet);
    }

    @Test
    void exercise2() {
        HashSet<String> hashSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        for (String str : hashSet) {
            System.out.println(str);
        }
    }

    @Test
    void exercise3() {
        HashSet<String> hashSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        System.out.println(hashSet.size());
    }

    @Test
    void exercise4() {
        HashSet<String> hashSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        System.out.println(hashSet);
        hashSet.removeAll(hashSet);
        System.out.println(hashSet);
    }

    @Test
    void exercise5() {
        HashSet<String> hashSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        System.out.println("HashSet is empty?: " + hashSet.isEmpty());
        hashSet.removeAll(hashSet);
        System.out.println("HashSet is empty?: " + hashSet.isEmpty());
    }

    @Test
    void exercise6() {
        HashSet<String> hashSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        System.out.println("Original Hash Set: " + hashSet);
        HashSet<String> newHashSet = new HashSet<String>();
        newHashSet = (HashSet)hashSet.clone();
        System.out.println("Cloned Hash Set: " + newHashSet);
    }

    @Test
    void exercise7() {
        HashSet<String> hashSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        String[] newArray = new String[hashSet.size()];
        hashSet.toArray(newArray);

        System.out.println("Arrays elements: ");
        for (String element: newArray) {
            System.out.println(element);
        }
    }

    @Test
    void exercise8() {
        HashSet<String> hashSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        TreeSet<String> treeSet = new TreeSet<>(hashSet);
        System.out.println(treeSet);
    }

    @Test
    void exercise9() {
        HashSet<String> hashSet = new HashSet<>(Arrays.asList("a", "b", "c"));
        List<String> list = new ArrayList<>(hashSet);
        System.out.println(list);
    }

    @Test
    void exercise10() {
        HashSet<String> hashSet1 = new HashSet<>(Arrays.asList("a1", "b1", "c1"));
        HashSet<String> hashSet2 = new HashSet<>(Arrays.asList("a2", "b2", "c2"));

        for (String element: hashSet1) {
            System.out.println(hashSet2.contains(element) ? "Yes" : "No");
        }
    }

    @Test
    void exercise11() {
        HashSet<String> hashSet1 = new HashSet<>(Arrays.asList("a1", "b1", "c1", "b2"));
        HashSet<String> hashSet2 = new HashSet<>(Arrays.asList("a2", "b2", "c2"));

        hashSet1.retainAll(hashSet2);

        System.out.println(hashSet1);
    }

    @Test
    void exercise12() {
        HashSet<String> hashSet = new HashSet<>(Arrays.asList("a1", "b1", "c1", "b2"));
        System.out.println(hashSet);
        hashSet.clear();
        System.out.println(hashSet);
    }
}

