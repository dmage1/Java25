package com.example.data;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Primitive Data Types")
public class PrimitiveDataTypes {
    // Primitive types are the foundation for all other data types
    // within the Java programming language.

    @DisplayName("boolean")
    @Test
    void testBoolean() {
        // represents a true or false value
        boolean b=true;
        assertTrue(b);
        assertFalse(!b);
    }

    @DisplayName("int's")
    @Test
    void testInt() {
        // represents a whole number
        int i = 5;
        assertEquals(5, i);
    }

    @DisplayName("double's")
    @Test
    void testDouble() {
        // represents a decimal number
        double d = 50.00d;
        assertEquals(50d, d);

        // the value 0 if d1 is numerically equal to d2;
        assertEquals(0, Double.compare(50d, d));
        // a value less than 0 if d1 is numerically less than d2;
        assertTrue(Double.compare(49d, d)<0);
        // and a value greater than 0 if d1 is numerically greater than d2.
        assertTrue(Double.compare(51d, d)>0);
    }

    @DisplayName("char's")
    @Test
    void testPrimitiveDataTypes() {
        // represents a single letter or symbol
        char c='a';

        assertEquals('a', c);
    }
}
