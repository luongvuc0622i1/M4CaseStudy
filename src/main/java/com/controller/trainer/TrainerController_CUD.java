package com.controller.trainer;

import com.model.trainer.Trainer;
import com.repository.trainer.ITrainerRepository;
import com.service.trainer.ITrainerService_CUD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/football")
public class TrainerController_CUD {
    @Autowired
    private ITrainerRepository trainerRepository;
    @Autowired
    private ITrainerService_CUD trainerService_cud;
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Trainer> createTrainer(@RequestBody Trainer trainer){
        return new ResponseEntity<>(trainerService_cud.save(trainer), HttpStatus.CREATED);
    }
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Trainer> updateTrainer(@PathVariable Long id,@RequestBody Trainer trainer){
        Optional<Trainer> trainerOptional=trainerService_cud.findById(id);
        if(!trainerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        trainer.setId(trainerOptional.get().getId());
        return new ResponseEntity<>(trainerService_cud.save(trainer),HttpStatus.OK);
    }
}
