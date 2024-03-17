package com.example.collections.array;

import java.util.ArrayList;

public class ArrayListDemo {

    /**
     * The ArrayList class extends AbstractList and implements the List interface. ArrayList supports dynamic arrays
     * that can grow as needed.
     *
     * Standard Java arrays are of a fixed length. After arrays are created, they cannot grow or shrink, which means
     * that you must know in advance how many elements an array will hold.
     *
     * Array lists are created with an initial size. When this size is exceeded, the collection is automatically
     * enlarged. When objects removed, the array may be shrunk.
     */
    public static void main(String args[]) {
        // create an array list
        ArrayList al = new ArrayList();
        System.out.println("Initial size of al: " + al.size());

        // add elements to the array list
        al.add("C");
        al.add("A");
        al.add("E");
        al.add("B");
        al.add("D");
        al.add("F");
        al.add(1, "A2"); // other pushed along
        System.out.println("Size of al after additions: " + al.size());

        // display the array list
        System.out.println("Contents of al: " + al);

        // Remove elements from the array list
        al.remove("F");
        al.remove(2); // remove 2
        System.out.println("Size of al after deletions: " + al.size());
        System.out.println("Contents of al: " + al);
    }
}
