package com.controller.player;

import com.model.player.Player;
import com.service.player.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/player")
public class CRUDPlayerController {
    @Autowired
    private IPlayerService iPlayerService;

    @GetMapping("/findAllPlayer")
    public ResponseEntity<Iterable<Player>> findAllPlayer() {
        List<Player> players = (List<Player>) iPlayerService.findAll();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
        return new ResponseEntity<>(iPlayerService.save(player), HttpStatus.OK);
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
