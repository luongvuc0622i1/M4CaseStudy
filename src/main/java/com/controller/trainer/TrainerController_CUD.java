package com.controller.trainer;

import com.model.DTO.PlayerDto;
import com.model.player.Player;
import com.model.trainer.Trainer;
import com.model.trainer.TrainerForm;
import com.repository.trainer.ITrainerRepository;
import com.service.trainer.ITrainerService_CUD;
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
    @RequestMapping(method = RequestMethod.PUT,value = "/{id}")
    public RedirectView createTrainer (@ModelAttribute TrainerForm playerDto) {
        MultipartFile image = playerDto.getCvFile();
        String imageName = image.getOriginalFilename();
        try {
            FileCopyUtils.copy(playerDto.getCvFile().getBytes(), new File(imageUpload + imageName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Trainer trainer = new Trainer(playerDto.getName(), playerDto.getDateOfBirth(), playerDto.getAddress(),
                playerDto.getIncome(),playerDto.getAppUser());
        trainerService_cud.save(trainer);
        return new RedirectView("/trainer");
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
