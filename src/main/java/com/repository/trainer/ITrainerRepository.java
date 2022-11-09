package com.repository.trainer;

import com.model.trainer.Trainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrainerRepository extends PagingAndSortingRepository<Trainer, Long> {
    Page<Trainer> findTrainerByNameContaining(String name,Pageable pageable);

    @Query(nativeQuery = true, value = "select * , (trainer_income.salary + trainer_income.bonus) as income from trainer join trainer_income on trainer.income_id = trainer_income.id order by income ASC ;")
    Page<Trainer> sortTrainerSalaryAsc(Pageable pageable);

    @Query(nativeQuery = true, value = "select * , (trainer_income.salary + trainer_income.bonus) as income from trainer join trainer_income on trainer.income_id = trainer_income.id order by income DESC ;")
    Page<Trainer> sortTrainerSalaryDesc(Pageable pageable);
}