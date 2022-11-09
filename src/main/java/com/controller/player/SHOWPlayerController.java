package com.controller.player;

import com.model.player.Performance;
import com.model.player.Player;
import com.model.player.Position;
import com.model.player.Status;
import com.service.player.IPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/player")
public class SHOWPlayerController {

    @Autowired
    private IPlayerService playerService;

    @GetMapping("/list-player")
    public ResponseEntity<Iterable<Player>> getPlayers() {
        Iterable<Player> players = playerService.findAll();
        if (!players.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/pagePlayer")
    public ResponseEntity<Page<Player>> showPagePlayer(@PageableDefault(value = 5)Pageable pageable) {
        Page<Player> players = playerService.findPage(pageable);
        if (!players.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/find-player-by-id/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Optional<Player> playerOptional = playerService.findById(id);
        return playerOptional.map(player -> new ResponseEntity<>(player,HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("/position")
    public ResponseEntity<Iterable<Position>> getPosition() {
        Iterable<Position> playerPositions = playerService.findAllPosition();
        if (!playerPositions.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerPositions, HttpStatus.OK);
    }

    @GetMapping("/performance")
    public ResponseEntity<Iterable<Performance>> getPerformance() {
        Iterable<Performance> playerPerformance = playerService.findAllPerformance();
        if (!playerPerformance.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerPerformance, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<Iterable<Status>> getStatus() {
        Iterable<Status> playerStatus = playerService.findAllStatus();
        if (!playerStatus.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerStatus, HttpStatus.OK);
    }

    @GetMapping("/search-player")
    public ResponseEntity<Iterable<Player>> getPlayerByName(@RequestParam("search") String search) {
        Iterable<Player> players = playerService.findAllByName(search);
        if (!players.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/sort-salary-asc")
    public ResponseEntity<Iterable<Player>> sortPlayerSalaryAsc() {
        Iterable<Player> players = playerService.sortPlayerSalaryAsc();
//        if (!players.iterator().hasNext()) {
//            new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/sort-salary-desc")
    public ResponseEntity<Iterable<Player>> sortPlayerSalaryDesc() {
        Iterable<Player> players = playerService.sortPlayerSalaryDesc();
        return new ResponseEntity<>(players, HttpStatus.OK);

    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Player>> searchByName(@PageableDefault(value = 2) @RequestParam Optional<String> name,Pageable pageable){
        Page<Player> players = playerService.findPage(pageable);
        if (players.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (name.isPresent()) {
            return new ResponseEntity<>(playerService.findPlayerByNameContaining(name.get(), pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }
}
