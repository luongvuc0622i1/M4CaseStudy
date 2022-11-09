package com.controller.player;

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

    @Value("${upload_file_avatar}")
    private String upload_file_avatar;

    @Autowired
    private IPlayerService iPlayerService;
    @Autowired
    private Environment env;

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

    @PostMapping("/player/create")
    public ResponseEntity<Player> addPlayer(@ModelAttribute("player") Player player, @ModelAttribute("avaFile") MultipartFile avaFile) {
        String path = servletContext.getRealPath("/");
        System.out.println("path: "+ path);
        if (avaFile != null) {
            String avaFileName = avaFile.getOriginalFilename();
            try {
                FileCopyUtils.copy(avaFile.getBytes(), new File(upload_file_avatar + avaFileName));
                player.setImage("/image/" + avaFileName);
            } catch (IOException ex) {
                player.setImage("image/Error");
                System.out.println("Loi khi upload File");
                ex.printStackTrace();
            }
        }
        iPlayerService.save(player);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

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
