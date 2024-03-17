package com.example.model;

import com.example.model.comparator.StudentRollnoComparator;
import lombok.ToString;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@ToString
public class Student {

    private int rollno;
    private String name, address;

    // Constructor
    public Student(int rollno, String name, String address)
    {

        // This keyword refers to current instance itself
        this.rollno = rollno;
        this.name = name;
        this.address = address;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static List<Student> getStudentList() {
        return Arrays.asList(
                new Student(111, "Dave", "Belfast"),
                new Student(131, "Elodie", "London"),
                new Student(121, "Gurt", "Glasgow"),
                new Student(151, "Bob", "Luton"),
                new Student(141, "Sue", "Lisburn"));
    }

    public static void main(String[] args) {
        List<Student> students = Student.getStudentList();

        System.out.println("Before Sorting : " + students);
        Collections.sort(students, new StudentRollnoComparator());
        System.out.println("After Sorting : " + students);
    }
}
