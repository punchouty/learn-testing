package com.racloop.learn.testing.simple;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class SimpleMockitoDemo {

    @Mock
    Map<String, Integer> mockedMap;

    @Test
    void testPut() {
        mockedMap.put("Rajan", 100);
    }
}
