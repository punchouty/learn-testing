package com.racloop.learn.testing.simple;

import org.junit.jupiter.api.*;

public class TestLifeCycle {
    @BeforeEach
    void setUp() {
        System.out.println("Setup");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Tear Down");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Tear one time");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Setup one time");
    }

    @Test
    void test1() {
        System.out.println("Test 1");
    }

    @Test
    void test2() {
        System.out.println("Test 2");
    }

    @Test
    void test3() {
        System.out.println("Test 3");
    }
}
