package com.controller.player;

import com.model.DTO.PlayerDto;
import com.model.player.Player;
import com.service.player.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

//    @Value("${upload_file_avatar}")
//    private String upload_file_avatar;

    @Autowired
    private IPlayerService iPlayerService;
    @GetMapping("/findAllPlayer")
    public ResponseEntity<Iterable<Player>> findAllPlayer() {
        List<Player> players = (List<Player>) iPlayerService.findAll();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }
//
//    @PostMapping("/create")
//    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
//        return new ResponseEntity<>(iPlayerService.save(player), HttpStatus.OK);
//    }

//    @PostMapping("/createAPI")
//    public ResponseEntity<Player> create(@ModelAttribute PlayerDto player){
//        Player player1 = new Player(player.getName(), player.getAddress());
//        MultipartFile multipartFile = player.getImage();
//        String fileName = multipartFile.getOriginalFilename();
//        String fileUpload = env.getProperty("upload.path").toString();
//        try {
//            FileCopyUtils.copy(player.getImage().getBytes(), new File(fileUpload + fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        player1.setImage(fileName);
//        iPlayerService.save(player1);
//        return new ResponseEntity<>(HttpStatus.ACCEPTED);
//    }

    @PutMapping("/player/edit/{id}")
    public ResponseEntity<Player> editPlayer(@PathVariable Long id, @RequestBody Player player) {
        Optional<Player> playerOptional = iPlayerService.findById(id);
        if (!playerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        player.setId(id);
        iPlayerService.save(player);
        return new ResponseEntity<>(player, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Player> deletePlayer(@PathVariable Long id) {
        Optional<Player> playerOptional = iPlayerService.findById(id);
        if (!playerOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iPlayerService.remove(id);
        return new ResponseEntity<>(playerOptional.get(), HttpStatus.NO_CONTENT);
    }
    
}
