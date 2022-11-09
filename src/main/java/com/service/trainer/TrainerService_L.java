package com.service.trainer;

import com.model.trainer.Trainer;

import java.util.Optional;

public class TrainerService_L implements ITrainerService_L {
    @Override
    public Iterable<Trainer> findAll() {
        return null;
    }

    @Override
    public Optional<Trainer> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Trainer save(Trainer model) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
