package com.repository.trainer;

import com.model.trainer.Income;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IIncomeRepository extends CrudRepository<Income, Long> {
}