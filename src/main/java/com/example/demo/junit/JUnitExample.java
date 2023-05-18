package com.example.demo.junit;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.*;

public class JUnitExample {

    // https://www.baeldung.com/junit-5

    private static Logger logger = LogManager.getLogger(JUnitExample.class);

    @BeforeAll
    static void setup() {
        logger.info("@BeforeAll - executes once before all test methods in this class");
    }

    @AfterAll
    static void done() {
        logger.info("@AfterAll - executed after all test methods.");
    }

    @BeforeEach
    void init() {
        logger.info("@BeforeEach - executes before each test method in this class");
    }

    @AfterEach
    void tearDown() {
        logger.info("@AfterEach - executed after each test method.");
    }

    @DisplayName("Single test successful")
    @Test
    void testSingleSuccessTest() {
        logger.info("Success");
    }

    @Test
    @Disabled("Not implemented yet")
    void testShowSomething() {
    }
}
