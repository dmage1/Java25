package com.example.collections;

import com.example.model.Student;
import com.example.model.comparator.StudentRollnoComparator;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ComparatorSort {

    // Main driver method
    public static void main(String[] args)
    {
        // Creating an empty ArrayList
        List<Student> ar = Student.getStudentList();

        // Display message for better readability
        System.out.println("Unsorted");

        // Printing list of students
        for (int i = 0; i < ar.size(); i++)
            System.out.println(ar.get(i));

        // Sorting a list of students in descending order of
        // roll numbers using a Comparator
        // that is reverse of Sortbyroll()
        Comparator c = Collections.reverseOrder(new StudentRollnoComparator());
        Collections.sort(ar, c);

        // Display message for better readability
        System.out.println("\nSorted by rollno");

        // Printing sorted students in descending order
        for (int i = 0; i < ar.size(); i++)
            System.out.println(ar.get(i));
    }
}