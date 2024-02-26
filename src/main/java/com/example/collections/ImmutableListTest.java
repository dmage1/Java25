package com.example.collections;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImmutableListTest {
    // Create an immutable list using the Java 9 factory of() method
    List<String> fruits = List.of("apple", "banana", "orange", "grape");

    @Test
    // assertThrows
    void whenAssertingUnsupportedOperationException_thenThrown() {
        Exception exception = assertThrows(
                UnsupportedOperationException.class,
                () -> {
                    fruits.add("pineapple");
                }
        );

    }
}
