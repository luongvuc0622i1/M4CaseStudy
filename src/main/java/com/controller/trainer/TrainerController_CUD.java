package com.controller.trainer;

import com.model.DTO.PlayerDto;
import com.model.player.Player;
import com.model.trainer.Trainer;
import com.model.trainer.TrainerForm;
import com.model.trainer.TrainerIncome;
import com.repository.trainer.ITrainerRepository;
import com.service.trainer.ITrainerService_CUD;
import com.service.trainer.TrainerIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/trainer")
public class TrainerController_CUD {
    @Value("${image.upload.path}")
    private String imageUpload;
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
    @RequestMapping(method = RequestMethod.PUT,value = "edit/{id}")
    public ResponseEntity<Trainer> editTrain(@PathVariable Long id, @RequestBody Trainer trainer) {
        Optional<Trainer> playerOptional = trainerService_cud.findById(id);
        if (!playerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        trainer.setId(id);
//        MultipartFile cvFile = trainer.getCvFile();
//        String imageName = cvFile.getOriginalFilename();
//        try {
//            FileCopyUtils.copy(trainer.getCvFile().getBytes(), new File(imageUpload + imageName));
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        Trainer trainer1 =new Trainer(trainer.getName(), trainer.getDateOfBirth(), trainer.getAddress(),
//                trainer.getIncome(), trainer.getAppUser());
        //    String name, Date dateOfBirth, String address, TrainerIncome income, AppUser appUser
        trainerService_cud.save(trainer);
        return new ResponseEntity<>(trainer, HttpStatus.OK);
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
    @Autowired
    private TrainerIncomeService trainerIncomeService;
    @RequestMapping(method = RequestMethod.GET,value = "editIncome/{id}")
    public ResponseEntity<TrainerIncome> findTrainerIncomeById(@PathVariable Long id){
        Optional<TrainerIncome> trainerOptional=trainerIncomeService.findById(id);
        if(!trainerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trainerOptional.get(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "editIncome/{id}")
    public ResponseEntity<TrainerIncome> editIncome(@PathVariable Long id, @RequestBody TrainerIncome trainer) {
        Optional<TrainerIncome> trainerIncomeOptional = trainerIncomeService.findById(id);
        if (!trainerIncomeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        trainer.setId(id);
        trainerIncomeService.save(trainer);
        return new ResponseEntity<>(trainer, HttpStatus.OK);
    }
}
