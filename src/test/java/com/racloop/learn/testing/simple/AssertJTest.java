package com.racloop.learn.testing.simple;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AssertJTest {
    private List<String> list;

    @BeforeEach
    void setUp() {
        list = new ArrayList<>();
        list.add("Rajan");
    }

    @Test
    void testLength() {
        assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void testLengthNegative() {
        assertThat(list.size()).isNotEqualTo(21);
    }

    @Test
    void testNotNull() {
        assertThat(list.get(0)).isNotNull();
    }

    @Test
    void testException() {
        assertThatThrownBy(() -> list.get(1))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }

}
