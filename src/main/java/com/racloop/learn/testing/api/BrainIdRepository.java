package com.racloop.learn.testing.api;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BrainIdRepository extends MongoRepository<BrainId, Long> {
    List<BrainId> findByDomain(String domain);
}
