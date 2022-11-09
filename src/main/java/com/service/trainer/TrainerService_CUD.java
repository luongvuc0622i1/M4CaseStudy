package com.service.trainer;

import com.model.trainer.Trainer;
import com.repository.trainer.ITrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainerService_CUD implements ITrainerService_CUD {
//    public static List<Trainer> trainerList=new ArrayList<Trainer>();
    @Autowired
    private ITrainerRepository trainerRepository;
    @Override
    public Iterable<Trainer> findAll() {
        return null;
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

//    @Override
//    public void add(Trainer trainer) {
//        trainerRepository.save(trainer);
//    }
}
