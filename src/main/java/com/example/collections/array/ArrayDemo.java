package com.example.collections.array;

import java.util.Arrays;

public class ArrayDemo {

    /**
     * This class contains various methods for manipulating arrays (such as sorting and searching).
     *
     * The methods in this class throw a NullPointerException if the specified array reference is null.
     */
    public static void main(String[] args) {
        // Declaring an array
        int arr[] = {8, 5, 3, 10, 2, 1, 15, 20};

        // Sorting the array
        Arrays.sort(arr);

        // Taking an element to search
        int ele = 15;

        // Using binarySearch() method to search "ele"
        System.out.println(ele + " presents at the index = " + Arrays.binarySearch(arr, ele));
    }
}
