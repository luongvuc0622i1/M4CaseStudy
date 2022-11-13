package com.controller.player;

import com.model.DTO.PlayerDto;
import com.model.player.*;
import com.model.trainer.TrainerIncome;
import com.service.player.IPlayerIncomeService;
import com.service.player.IPlayerService;
import com.service.trainer.TrainerIncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/player")
public class CRUDPlayerController {
    @Autowired
    private ServletContext servletContext;

//    @Autowired
//    Environment env;

    @Value("${image.upload.path}")
    private String imageUpload;

    @Value("${file.upload.path}")
    private String profileUpload;

    @Autowired
    private IPlayerService playerService;

    @GetMapping("/findAllPlayer")
    public ResponseEntity<Iterable<Player>> findAllPlayer() {
        List<Player> players = (List<Player>) playerService.findAll();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Player> editPlayer(@PathVariable Long id, @RequestBody Player player) {
        Optional<Player> playerOptional = playerService.findById(id);
        if (!playerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        player.setId(id);

        playerService.save(player);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable Long id) {
        Optional<Player> playerOptional = playerService.findById(id);
        if (!playerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        playerService.remove(id);
        return new ResponseEntity<>(playerOptional.get(), HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public RedirectView createPlayer (@ModelAttribute PlayerDto playerDto) {
        MultipartFile image = playerDto.getImage();
        String imageName = image.getOriginalFilename();

        MultipartFile profile = playerDto.getProfile();
        String profileName = profile.getOriginalFilename();
        try {
            FileCopyUtils.copy(playerDto.getImage().getBytes(), new File(imageUpload + imageName));
            FileCopyUtils.copy(playerDto.getProfile().getBytes(), new File(profileUpload + profileName));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Player player = new Player(playerDto.getName(), playerDto.getDateOfBirth(), playerDto.getAddress(),
                playerDto.getHeight(), playerDto.getWeight(), playerDto.getAppUser(), playerDto.getPosition(), playerDto.getPerformance(),
                playerDto.getPlayerIncome(), profileName, imageName, playerDto.getStatus());
        playerService.save(player);
        return new RedirectView("/player/page-player");
    }

    @Autowired
    private IPlayerIncomeService playerIncomeService;
    @RequestMapping(method = RequestMethod.GET,value = "editIncome/{id}")
    public ResponseEntity<PlayerIncome> findPlayerIncomeById(@PathVariable Long id){
        Optional<PlayerIncome> playerOptional= playerIncomeService.findById(id);
        if(!playerOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(playerOptional.get(),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT,value = "editIncome/{id}")
    public ResponseEntity<PlayerIncome> editIncome(@PathVariable Long id, @RequestBody PlayerIncome player) {
        Optional<PlayerIncome> playerIncomeOptional = playerIncomeService.findById(id);
        if (!playerIncomeOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        player.setId(id);
        playerIncomeService.save(player);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }

}
