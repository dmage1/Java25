package com.example.model;

import com.example.model.comparator.PersonAgeComparator;
import lombok.ToString;

import java.util.*;

@ToString
public class Person {

    private String name;
    private int age;
    private String password;

    public Person(String name, int age) {
        this(name, age, null);
    }

    public Person(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public Optional<Integer> getAge() {
        return Optional.ofNullable(age);
    }

    public Optional<String> getPassword() {
        return Optional.ofNullable(password);
    }

    // normal constructors and setters

    public static List<Person> getPersonList() {
        return Arrays.asList(
                new Person("John", 22, "pass"),
                new Person("Roger", 20, "pass"),
                new Person("Steven", 24, "pass"));
    }

    public static void main(String[] args) {
        List<Person> people = Person.getPersonList();

        System.out.println("Before Sorting : " + people);
        Collections.sort(people, new PersonAgeComparator());
        System.out.println("After Sorting : " + people);
    }
}
