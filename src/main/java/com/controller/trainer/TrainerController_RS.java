package com.controller.trainer;

import com.model.trainer.Trainer;
import com.service.trainer.ITrainerService_RS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/trainer")
public class TrainerController_RS {
    @Autowired
    private ITrainerService_RS trainerService;

    @GetMapping
    public ResponseEntity<Iterable<Trainer>> findAllTrainer() {
        List<Trainer> customers = (List<Trainer>) trainerService.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Trainer>> displayTrainerPage(@PageableDefault(value = 2) @RequestParam Optional<String> name, Pageable pageable) {
        Page<Trainer> trainers = trainerService.findAllPage(pageable);
        if (trainers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (name.isPresent()) {
            return new ResponseEntity<>(trainerService.findTrainerByNameContaining(name.get(), pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(trainers, HttpStatus.OK);
    }

    @GetMapping("/page/sortAsc")
    public ResponseEntity<Page<Trainer>> sortCoachBySalaryAsc(@PageableDefault(value = 2) Pageable pageable){
        Page<Trainer> trainers = trainerService.sortTrainerSalaryAsc(pageable);
        return new ResponseEntity<>(trainers, HttpStatus.OK);
    }
    @GetMapping("/page/sortDesc")
    public ResponseEntity<Page<Trainer>> sortCoachBySalaryDesc(@PageableDefault(value = 2) Pageable pageable){
        Page<Trainer> trainers = trainerService.sortTrainerSalaryDesc(pageable);
        return new ResponseEntity<>(trainers, HttpStatus.OK);
    }

    @GetMapping("/searchByName")
    public ResponseEntity<Iterable<Trainer>> searchByName(@PageableDefault(value = 2) @RequestParam Optional<String> name, Pageable pageable){
        Page<Trainer> trainers = trainerService.findAllPage(pageable);
        if (trainers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (name.isPresent()) {
            return new ResponseEntity<>(trainerService.findTrainerByNameContaining(name.get(), pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(trainers, HttpStatus.OK);
    }
}
