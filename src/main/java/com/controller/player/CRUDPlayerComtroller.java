package com.controller.player;

import com.model.player.Player;
import com.service.player.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/players")
public class CRUDPlayerComtroller {
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
}
