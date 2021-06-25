package com.racloop.learn.testing.api;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrainIdServiceImpl implements BrainIdService {

    private final BrainIdRepository brainIdRepository;

    public BrainIdServiceImpl(BrainIdRepository brainIdRepository) {
        this.brainIdRepository = brainIdRepository;
    }

    @Override
    public List<BrainId> all() {
        return brainIdRepository.findAll();
    }

    @Override
    public List<BrainId> search(String domain) {
        return brainIdRepository.findByDomain(domain);
    }

    @Override
    public BrainId save(BrainId brainId) {
        if(brainId.getId() == null) {
            throw new InvalidDataException("Id cannot be null");
        }
        return brainIdRepository.save(brainId);
    }

    @Override
    public BrainId edit(Long id, BrainId brainId) {
        Optional<BrainId> optional = brainIdRepository.findById(id);
        if(optional.isEmpty()) {
            throw new InvalidDataException("Id not present");
        }
        BrainId brainIdSaved = optional.get();
        brainIdSaved.setKeyToken(brainId.getKeyToken());
        brainIdSaved.setDomain(brainId.getDomain());
        return brainIdRepository.save(brainIdSaved);
    }

    @Override
    public void delete(Long id) {
        brainIdRepository.deleteById(id);
    }


}
