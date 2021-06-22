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
        BrainId brainId = new BrainId();
        brainId.setDomain("common");
        brainId.setId(1L);
        brainId.setKeyToken("kt-1");
        brainIdRepository.save(brainId);
        brainId = new BrainId();
        brainId.setDomain("common");
        brainId.setId(2L);
        brainId.setKeyToken("kt-2");
        brainIdRepository.save(brainId);
    }

    @Test
    void findByKeyToken() {
        List<BrainId> ids = brainIdRepository.findByDomain("common");
        assertEquals(2, ids.size());
    }
}