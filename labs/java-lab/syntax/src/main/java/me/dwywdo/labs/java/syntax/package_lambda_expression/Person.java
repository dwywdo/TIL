package me.dwywdo.labs.java.syntax.package_lambda_expression;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class Person {
    public static List<Person> createRoster() {
        return Arrays.asList(
                new Person(),
                new Person(),
                new Person()
        );
    }

    public enum Sex {
        MALE, FEMALE
    }

    String name;
    LocalDate birthday;
    Sex gender;
    String emailAddress;

    public int getAge() {
        return 0;
    }

    public void printPerson() {

    }

    public Sex getGender() {
        return gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
