package com.example.model.comparator;

import com.example.model.Person;

import java.util.Comparator;

public class PersonAgeComparator implements Comparator<Person> {

    @Override
    public int compare(Person firstPerson, Person secondPerson) {
        return Integer.compare(firstPerson.getAge().orElse(0), secondPerson.getAge().orElse(0));
    }
}
