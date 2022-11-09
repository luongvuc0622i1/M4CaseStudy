package com.service.trainer;

import com.model.trainer.Trainer;
import com.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITrainerService_RS extends IGeneralService<Trainer> {
    Page<Trainer> findAllPage(Pageable pageable);

//    Page<Trainer> findTrainerByRoleContaining(String role, Pageable pageable);
//
//    Iterable<Trainer> sortTrainerSalaryAsc();
//
//    Iterable<Trainer> sortTrainerSalaryDesc();

    Page<Trainer> findTrainerByNameContaining(String name,Pageable pageable);
}
