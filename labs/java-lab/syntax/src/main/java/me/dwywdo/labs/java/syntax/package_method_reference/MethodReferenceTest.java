package me.dwywdo.labs.java.syntax.package_method_reference;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MethodReferenceTest {
    public static void main(String[] args) {
        List<Person> roster = Person.createRoster();
        Person[] rosterAsArray = roster.toArray(new Person[roster.size()]);

        class PersonAgeComparator implements Comparator<Person> {
            public int compare(Person o1, Person o2) {
                return o1.getBirthday().compareTo(o2.getBirthday());
            }
        }

        // Approach 1 (Implement Comparator and create an instance of it)
        Arrays.sort(rosterAsArray, new PersonAgeComparator());


        // Approach 2 (Use Lambda Expression)
        Arrays.sort(
                rosterAsArray,
                (Person a, Person b) -> { return a.getBirthday().compareTo(b.getBirthday()); }
        );

        // Approach 3 (Use existing method in Lambda Expression)
        Arrays.sort(rosterAsArray, (a, b) -> Person.compareByAge(a, b));

        // Approach 4 (Method Reference)
        Arrays.sort(rosterAsArray, Person::compareByAge);
    }
}
