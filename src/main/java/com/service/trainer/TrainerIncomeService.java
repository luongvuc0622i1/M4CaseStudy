package com.service.trainer;

import com.model.trainer.TrainerIncome;
import com.repository.trainer.ITrainerIncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class TrainerIncomeService implements ITrainerIncomeService{
    @Autowired
    ITrainerIncomeRepository trainerIncomeRepository;

    @Override
    public Iterable<TrainerIncome> findAll() {
        return trainerIncomeRepository.findAll();
    }

    @Override
    public Optional<TrainerIncome> findById(Long id) {
        return trainerIncomeRepository.findById(id);
    }

    @Override
    public TrainerIncome save(TrainerIncome model) {
        return trainerIncomeRepository.save(model);
    }

    @Override
    public void remove(Long id) {

    }
}
