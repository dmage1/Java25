package com.example.collections.array;

import org.junit.jupiter.api.Test;

import java.util.ArrayDeque;
import java.util.Deque;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * A Stack is a linear data structure where removal and insertion occur at the same end. A Queue is also a linear data
 * structure, but removal and insertion happen at different ends. A Stack follows the Last In, First Out (LIFO)
 * principle, meaning the most recently inserted element is removed first.
 */

/**
 * Insertion from Head	offerFirst(e)	addFirst(e)
 * Removal from Head	pollFirst()	    removeFirst()
 * Retrieval from Head	peekFirst()	    getFirst()
 * Insertion from Tail	offerLast(e)	addLast(e)
 * Removal from Tail	pollLast()	    removeLast()
 * Retrieval from Tail	peekLast()	    getLast()
 */
public class ArrayDequeTest {

    // Using ArrayDeque as a Stack

    @Test
    public void whenPush_addsAtFirst() {
        Deque<String> stack = new ArrayDeque<>();

        // Push elemenst onto the ArrayDeque – when used as a Stack:
        stack.push("first");
        stack.push("second");

        assertEquals("second", stack.getFirst());
        assertEquals("first", stack.getLast());
        stack.forEach((n) -> System.out.println(n));
    }

    @Test
    public void whenPop_removesLast() {
        Deque<String> stack = new ArrayDeque<>();
        stack.push("first");
        stack.push("second");

        // Pop an element from the ArrayDeque – when used as a Stack:
        assertEquals("second", stack.pop());

        assertEquals("first", stack.getFirst());
        assertEquals("first", stack.getLast());
        stack.forEach((n) -> System.out.println(n));
    }

    // Using ArrayDeque as a Queue

    @Test
    public void whenOffer_addsAtLast() {
        Deque<String> queue = new ArrayDeque<>();

        // Offer elements onto the ArrayDeque – when used as a Queue:
        queue.offer("first");
        queue.offer("second");

        assertEquals("first", queue.getFirst());
        assertEquals("second", queue.getLast());
        queue.forEach((n) -> System.out.println(n));
    }

    @Test
    public void whenPoll_removesFirst() {
        Deque<String> queue = new ArrayDeque<>();
        queue.offer("first");
        queue.offer("second");

        // Poll an element from the ArrayDeque – when used as a queue:
        assertEquals("first", queue.poll());
        queue.forEach((n) -> System.out.println(n));
    }

}
