package com.racloop.learn.testing.mockito;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class SimpleMockitoDemo {

    @Mock
    Map<String, Integer> mockedMap;

    @Mock
    HashMap<String, Integer> mockedHashMap;

    @Test
    void testPutInterface() {
        Integer number = mockedMap.get("Rajan");
        System.out.println(number);
    }

    @Test
    void testPutImplementation() {
        Integer number = mockedHashMap.get("Rajan");
        System.out.println(number);
    }

    @Test
    void testImplementation() {
        mockedHashMap.put("Rajan", 100);
        Integer number = mockedHashMap.get("Rajan");
        System.out.println(number);
    }
}
