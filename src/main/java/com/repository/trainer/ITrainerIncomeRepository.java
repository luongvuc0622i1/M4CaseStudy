package com.repository.trainer;

import com.model.trainer.TrainerIncome;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrainerIncomeRepository extends CrudRepository<TrainerIncome, Long> {

}