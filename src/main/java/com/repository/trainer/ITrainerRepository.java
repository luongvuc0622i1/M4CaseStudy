package com.repository.trainer;

import com.model.trainer.Trainer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITrainerRepository extends PagingAndSortingRepository<Trainer, Long> {
//    Page<Trainer> findTrainerByRoleContaining(String role, Pageable pageable);
//    Page<Trainer> findTrainerByNameContaining(String name,Pageable pageable);

//    @Query(nativeQuery = true, value = "select * from trainer order by salary ASC ;")
//    Iterable<Trainer> sortTrainerSalaryAsc();
//
//    @Query(nativeQuery = true, value = "select * from trainer order by salary DESC ;")
//    Iterable<Trainer> sortTrainerSalaryDesc();
}