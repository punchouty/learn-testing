package com.racloop.learn.testing.api;

import java.util.List;

public interface BrainIdService {

    BrainId save(BrainId brainId);

    List<BrainId> search(String domain);

    BrainId edit(Long id, BrainId brainId);

    void delete(Long id);

    List<BrainId> all();
}
