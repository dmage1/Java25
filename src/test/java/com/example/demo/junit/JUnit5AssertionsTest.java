package com.example.demo.junit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import static java.time.Duration.ofSeconds;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class JUnit5AssertionsTest {

    private static Logger logger = LogManager.getLogger(JUnit5AssertionsTest.class);

    @Test
    // assertArrayEquals
    public void whenAssertingArraysEquality_thenEqual() {
        // The assertArrayEquals assertion verifies that the expected and actual arrays are equal:
        char[] expected = { 'J', 'u', 'p', 'i', 't', 'e', 'r' };
        char[] actual = "Jupiter".toCharArray();

        assertArrayEquals(expected, actual, "Arrays should be equal");
    }

    @Test
    // assertNotEquals
    void whenAssertingEquality_thenNotEqual() {
        // Complementary to assertEquals, the assertNotEquals assertion asserts that the expected and actual
        // values aren’t equal:
        Integer value = 5; // result of an algorithm

        assertNotEquals(0, value, "The result cannot be 0");
    }

    @Test
    void whenAssertingEquality_thenEqual() {
        // If we want to assert that two floats are equal, we can use the simple assertEquals assertion:
        float square = 2 * 2;
        float rectangle = 2 * 2;

        assertEquals(square, rectangle);
    }

    @Test
    void whenAssertingEqualityWithDelta_thenEqual() {
        //However, if we want to assert that the actual value differs by a predefined delta from the expected value,
        // we can still use the assertEquals, but we have to pass the delta value as the third parameter:
        float square = 2 * 2;
        float rectangle = 3 * 2;
        float delta = 2;

        assertEquals(square, rectangle, delta);
    }

    @Test
        // assertTrue and assertFalse
    void whenAssertingConditions_thenVerified() {
        // With the assertTrue assertion, it’s possible to verify the supplied conditions are true:
        assertTrue(5 > 4, "5 is greater the 4");
        assertTrue(null == null, "null is equal to null");
    }

    @Test
    public void givenBooleanSupplier_whenAssertingCondition_thenVerified() {
        // Thanks to the support of the lambda expression, it’s possible to supply a BooleanSupplier to the
        // assertion, instead of a boolean condition
        BooleanSupplier condition = () -> 5 > 6;

        assertFalse(condition, "5 is not greater then 6");
    }

    // assertNull and assertNotNull
    @Test
    void whenAssertingNotNull_thenTrue() {
        //  When we want to assert that an object is not null, we can use the assertNotNull assertion:
        Object dog = new Object();

        assertNotNull(dog, () -> "The dog should not be null");
    }

    @Test
    public void whenAssertingNull_thenTrue() {
        // Conversely, we can use the assertNull assertion to check if the actual is null
        Object cat = null;

        assertNull(cat, () -> "The cat should be null");
    }

    @Test
    // assertSame and assertNotSame
    void whenAssertingSameObject_thenSuccessfull() {
        // When we want to assert that the expected and actual refer to the same Object, we must use the assertSame assertion:
        String language = "Java";
        Optional<String> optional = Optional.of(language);

        assertSame(language, optional.get());
        // To achieve the opposite, we can use the assertNotSame one.
    }

    @Test
    // fail
    public void whenFailingATest_thenFailed() {
        // The fail assertion fails a test with the provided failure message, as well as the underlying cause.
        // This can be useful to mark a test when it’s development isn’t complete:

        // Test not completed
        fail("FAIL - test not completed");
    }

    @Test
    // assertAll
    void givenMultipleAssertion_whenAssertingAll_thenOK() {
        // This assertion allows the creation of grouped assertions, where all the assertions are executed and their
        // failures are reported together.
        Object obj = null;
        assertAll(
                "heading",
                () -> assertEquals(4, 2 * 2, "4 is 2 times 2"),
                () -> assertEquals("java", "JAVA".toLowerCase()),
                () -> assertNull(obj, "obj is null")
        );
    }



    @Test
    // assertIterableEquals
    void givenTwoLists_whenAssertingIterables_thenEquals() {
        // The assertIterableEquals asserts that the expected and actual iterables are deeply equal.
        Iterable<String> al = new ArrayList<>(asList("Java", "Junit", "Test"));
        Iterable<String> ll = new LinkedList<>(asList("Java", "Junit", "Test"));

        assertIterableEquals(al, ll);
        // In the same way as assertArrayEquals, if both iterables are null, they’re considered equal.
    }

    @Test
    void whenAssertingEqualityListOfStrings_thenEqual() {
        List<String> expected = asList("Java", "\\d+", "JUnit");
        List<String> actual = asList("Java", "11", "JUnit");
        logger.info(expected);
        logger.info(actual);

        assertLinesMatch(expected, actual);
    }

    @Test
    // assertThrows
    void whenAssertingException_thenThrown() {
        // the new assertThrows assertion allows us a clear and simple way to assert if an executable throws
        // the specified exception type.
        Throwable exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    throw new IllegalArgumentException("Exception message");
                }
        );
        assertEquals("Exception message", exception.getMessage());
    }

    @Test
    // assertTimeout and assertTimeoutPreemptively
    void whenAssertingTimeout_thenNotExceeded() {
        // If we want to assert that the execution of a supplied Executable ends before a given Timeout,
        // we can use the assertTimeout assertion:
        assertTimeout(
                ofSeconds(2),
                () -> {
                    // code that requires less than 2 minutes to execute
                    Thread.sleep(1000);
                }
        );
    }
}
