package com.controller.trainer;

import com.model.trainer.Trainer;
import com.repository.trainer.ITrainerRepository;
import com.service.trainer.ITrainerService_CUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/trainer")
public class TrainerController_CUD {
    @Autowired
    private ITrainerRepository trainerRepository;
    @Autowired
    private ITrainerService_CUD trainerService_cud;
    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public ResponseEntity<Trainer> findTrainerById(@PathVariable Long id){
        Optional<Trainer> trainerOptional=trainerService_cud.findById(id);
        if(!trainerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainerOptional.get(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer){
        return new ResponseEntity<>(trainerService_cud.save(trainer), HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public ResponseEntity<Trainer> updateTrainer(@PathVariable Long id,@RequestBody Trainer trainer){
        Optional<Trainer> trainerOptional=trainerService_cud.findById(id);
        if(!trainerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        trainer.setId(trainerOptional.get().getId());
        return new ResponseEntity<>(trainerService_cud.save(trainer),HttpStatus.OK);
    }
    @RequestMapping(method =RequestMethod.DELETE,value = "/{id}")
    public ResponseEntity<Trainer> deleteTrainer(@PathVariable Long id){
        Optional<Trainer> trainerOptional=trainerService_cud.findById(id);
        if(!trainerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        trainerService_cud.remove(id);
        return new ResponseEntity<>(trainerOptional.get(),HttpStatus.NO_CONTENT);
    }
}
