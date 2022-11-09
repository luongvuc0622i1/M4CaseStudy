package com.service.player;

import com.model.player.Performance;
import com.model.player.Player;
import com.model.player.Position;
import com.model.player.Status;
import com.repository.player.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService implements IPlayerService{

    @Autowired
    private IPlayerRepository playerRepository;

    @Autowired
    private IPositionRepository positionRepository;

    @Autowired
    private IPerformanceRepository performanceRepository;

    @Autowired
    private IStatusRepository statusRepository;

    @Autowired
    private IPlayerIncomeRepository playerIncomeRepository;

    @Override
    public Iterable<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public Player save(Player model) {
        return playerRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        playerRepository.deleteById(id);
    }

    @Override
    public Iterable<Position> findAllPosition() {
        return null;
    }

    @Override
    public Iterable<Performance> findAllPerformance() {
        return null;
    }

    @Override
    public Iterable<Status> findAllStatus() {
        return null;
    }

    @Override
    public Iterable<Player> findAllByName(String name) {
        return null;
    }

    @Override
    public Page<Player> findPage(Pageable pageable) {
        return playerRepository.findAll(pageable);
    }
}
