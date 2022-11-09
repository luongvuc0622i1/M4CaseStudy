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

    @Query("select t from Trainer t join TrainerIncome ti on t.income.id = ti.id order by (ti.salary + ti.bonus) ASC")
    Page<Trainer> sortTrainerSalaryAsc(Pageable pageable);

    @Query("select t from Trainer t join TrainerIncome ti on t.income.id = ti.id order by (ti.salary + ti.bonus) DESC")
    Page<Trainer> sortTrainerSalaryDesc(Pageable pageable);
}