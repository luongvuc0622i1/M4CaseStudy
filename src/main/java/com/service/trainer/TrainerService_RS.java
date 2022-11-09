package com.service.trainer;

import com.model.trainer.Trainer;
import com.repository.trainer.ITrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrainerService_RS implements ITrainerService_RS {
    @Autowired
    private ITrainerRepository trainerRepository;
    @Override
    public Iterable<Trainer> findAll() {
        return trainerRepository.findAll();
    }

    @Override
    public Optional<Trainer> findById(Long id) {
        return trainerRepository.findById(id);
    }

    @Override
    public Trainer save(Trainer model) {
        return trainerRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        trainerRepository.deleteById(id);
    }

    @Override
    public Page<Trainer> findAllPage(Pageable pageable) {
        return trainerRepository.findAll(pageable);
    }

    @Override
    public Page<Trainer> sortTrainerSalaryAsc(Pageable pageable) {
        return trainerRepository.sortTrainerSalaryAsc(pageable);
    }

    @Override
    public Page<Trainer> sortTrainerSalaryDesc(Pageable pageable) {
        return trainerRepository.sortTrainerSalaryDesc(pageable);
    }

    @Override
    public Page<Trainer> findTrainerByNameContaining(String name, Pageable pageable) {
        return trainerRepository.findTrainerByNameContaining(name, pageable);
    }
}
