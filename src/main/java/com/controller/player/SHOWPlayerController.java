package com.controller.player;

import com.model.player.Performance;
import com.model.player.Player;
import com.model.player.Position;
import com.model.player.Status;
import com.model.trainer.Trainer;
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

    @GetMapping("/page-player")
    public ResponseEntity<Page<Player>> showPagePlayer(@PageableDefault(value = 5) Pageable pageable) {
        Page<Player> players = playerService.findPage(pageable);
        if (!players.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/find-player-by-id/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Optional<Player> playerOptional = playerService.findById(id);
        return playerOptional.map(player -> new ResponseEntity<>(player, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
    public ResponseEntity<Iterable<Player>> getPlayerByName(@RequestParam("name") String name) {
        Iterable<Player> players = playerService.findAllByName(name);
        if (!players.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/sort-income-asc")
    public ResponseEntity<Page<Player>> sortPlayerSalaryAsc(@PageableDefault(value = 5) Pageable pageable) {
        Page<Player> players = playerService.sortPlayerSalaryAsc(pageable);
        if (!players.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/sort-income-desc")
    public ResponseEntity<Page<Player>> sortPlayerSalaryDesc(@PageableDefault(value = 5) Pageable pageable) {
        Page<Player> players = playerService.sortPlayerSalaryDesc(pageable);
        if (!players.iterator().hasNext()) {
            new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);

    }

    @GetMapping("/search-page-player/name")
    public ResponseEntity<Iterable<Player>> searchByName(@PageableDefault(value = 5) @RequestParam Optional<String> name, Pageable pageable) {
        Page<Player> players = playerService.findPage(pageable);
        if (players.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (name.isPresent()) {
            return new ResponseEntity<>(playerService.findPlayerByNameContaining(name.get(), pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/search-page-player/position")
    public ResponseEntity<Iterable<Player>> searchByPosition(@PageableDefault(value = 5) @RequestParam Optional<String> position, Pageable pageable) {
        Page<Player> players = playerService.findPage(pageable);
        if (players.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (position.isPresent()) {
            return new ResponseEntity<>(playerService.findPlayerByPositionContaining(position.get(), pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/search-page-player/status")
    public ResponseEntity<Iterable<Player>> searchByStatus(@PageableDefault(value = 5) @RequestParam Optional<String> status, Pageable pageable) {
        Page<Player> players = playerService.findPage(pageable);
        if (players.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (status.isPresent()) {
            return new ResponseEntity<>(playerService.findPlayerByStatusContaining(status.get(), pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Player>> displayPlayerPage(@PageableDefault(value = 2) @RequestParam Optional<String> name, Pageable pageable) {
        Page<Player> players = playerService.findAllPage(pageable);
        if (players.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (name.isPresent()) {
            return new ResponseEntity<>(playerService.findPlayerByNameContaining(name.get(), pageable), HttpStatus.OK);
        }
        return new ResponseEntity<>(players, HttpStatus.OK);
    }
}
