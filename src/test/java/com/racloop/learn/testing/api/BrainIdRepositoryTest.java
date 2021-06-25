package com.racloop.learn.testing.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class BrainIdRepositoryTest {

    @Autowired
    private BrainIdRepository brainIdRepository;

    @BeforeEach
    void setUp() {
        BrainId brainId = BrainId.builder().id(1L).domain("common").keyToken("key-token-1").build();
        brainIdRepository.save(brainId);
        brainId = BrainId.builder().id(2L).domain("common").keyToken("key-token-2").build();
        brainIdRepository.save(brainId);
        brainId = BrainId.builder().id(3L).domain("telecom").keyToken("key-token-3").build();
        brainIdRepository.save(brainId);
    }

    @Test
    void findByKeyToken() {
        List<BrainId> ids = brainIdRepository.findByDomain("common");
        assertEquals(2, ids.size());
    }
}