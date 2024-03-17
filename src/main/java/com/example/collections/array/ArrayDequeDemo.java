package com.example.collections.array;

import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeDemo {

    /**
     * Array deques have no capacity restrictions so they grow as necessary to support usage.
     *
     * They are not thread-safe; in the absence of external synchronization.
     *
     * They do not support concurrent access by multiple threads.
     *
     * Null elements are prohibited in the array deques.
     *
     * They are faster than Stack and LinkedList.
     */

    /**
     * Operation	        Method	        Method throwing Exception
     * Insertion from Head	offerFirst(e)	addFirst(e)
     * Removal from Head	pollFirst()	    removeFirst()
     * Retrieval from Head	peekFirst()	    getFirst()
     * Insertion from Tail	offerLast(e)	addLast(e)
     * Removal from Tail	pollLast()	    removeLast()
     * Retrieval from Tail	peekLast()	    getLast()
     *
     * @param args
     */
    public static void main(String[] args) {
        // The main() function
        Deque< Integer > objDeque = new ArrayDeque< >();
        // Adding elements at first and last
        objDeque.addFirst(15);
        objDeque.addLast(28);

        // Removing the elements
        int ele1 = objDeque.removeFirst();
        int ele2 = objDeque.removeLast();

        // Printing removed elements
        System.out.println("First removed element is : " + ele1);
        System.out.println("Last removed element is : " + ele2);
    }
}
