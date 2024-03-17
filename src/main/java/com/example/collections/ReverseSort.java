package com.example.collections;

import java.util.ArrayList;
import java.util.Collections;

public class ReverseSort {

    // Main driver method
    public static void main(String[] args)
    {
        // Creating a list of integers for which we
        // create an empty ArrayList by
        // declaring object of ArrayList class
        ArrayList<Integer> al = new ArrayList<Integer>();

        // Custom input integer elements
        al.add(30);
        al.add(20);
        al.add(10);
        al.add(40);
        al.add(50);

        // Using sort() method of Collections class to
        // sort the elements and passing list and using
        // reverseOrder() method to sort in descending order
        Collections.sort(al, Collections.reverseOrder());
        //Collections.shuffle(al);

        // Lastly printing the descending sorted list on
        // console
        System.out.println(
                "List after the use of Collection.reverseOrder()"
                        + " and Collections.sort() :\n" + al);
    }
}
