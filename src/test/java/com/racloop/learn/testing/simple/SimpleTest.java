package com.racloop.learn.testing.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

public class SimpleTest {

    private List<String> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
        list.add("Rajan");
    }

    @Test
    void testLength() {
        List<String> list = new ArrayList<>();
        list.add("Punchouty");
        Assertions.assertEquals(1, list.size());
    }

    @Test
    void testLengthNegative() {
        Assertions.assertNotEquals(2, list.size());
    }

    @Test
    void testNotNull() {
        Assertions.assertNotNull(list.get(0));
    }

    @Test
    void testException() {
        Assertions.assertThrows(IndexOutOfBoundsException.class, () -> {
            list.get(1);
        });
    }

    @Test
    @Disabled
    void testFail() {
        fail();
    }
}
