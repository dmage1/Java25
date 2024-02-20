package com.example.demo.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.util.AssertionErrors.assertNull;

public class JUnitAssertionTest {

    @Test
    // assertEquals
    public void whenAssertingEquality_thenEqual() {
        // The assertEquals assertion verifies that the expected and actual values are equal:
        String expected = "Dave";
        String actual = "Dave";

        assertEquals("success - strings are  equal", expected, actual);
    }

    @Test
    // assertArrayEquals
    public void whenAssertingArraysEquality_thenEqual() {
        // If we want to assert that two arrays are equals, we can use the assertArrayEquals:
        char[] expected = {'J','u','n','i','t'};
        char[] actual = "Junit".toCharArray();

        assertArrayEquals(expected, actual);
    }

    @Test
    public void givenNullArrays_whenAssertingArraysEquality_thenEqual() {
        // If both arrays are null, the assertion will consider them equal:
        int[] expected = null;
        int[] actual = null;

        assertArrayEquals(expected, actual);
    }

    @Test
    // assertNotNull and assertNull
    public void whenAssertingNull_thenTrue() {
        // When we want to test if an object is null, we can use the assertNull assertion:
        Object car = null;

        assertNull("The car should be null", car);

        // Conversely, if we want to assert that an object shouldn’t be null, we can use the assertNotNull assertion.
    }

    @Test
    // assertNotSame and assertSame
    public void whenAssertingNotSameObject_thenDifferent() {
        // With assertNotSame, it’s possible to verify that two variables don’t refer to the same object:
        Object cat = new Object();
        Object dog = new Object();

        assertNotSame(cat, dog);

        // Otherwise, when we want to verify that two variables do refer to the same object, we can use the assertSame assertion
    }

    @Test
    // assertTrue and assertFalse
    public void whenAssertingConditions_thenVerified() {
        // If we want to verify that a certain condition is true or false, we can use the assertTrue or assertFalse assertions, respectively:
        assertTrue(5 > 4, "5 is greater then 4");
        assertFalse(5 > 6, "5 is not greater then 6");
    }
}
