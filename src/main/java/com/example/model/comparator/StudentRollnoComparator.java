package com.example.model.comparator;

import com.example.model.Student;

import java.util.Comparator;

public class StudentRollnoComparator implements Comparator<Student> {
    @Override
    public int compare(Student a, Student b)
    {
        /**
         * Compares its two arguments for order.
         * Returns:-
         *  a negative integer, if the first argument is less than
         *  zero, if equal to
         *  a positive integer as the first argument is less than, equal to, or greater than the second.
         */
        return a.getRollno() - b.getRollno();
    }

}

