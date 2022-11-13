package com.service.player;

import com.model.player.PlayerIncome;
import com.repository.player.IPlayerIncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerIncomeService implements IPlayerIncomeService{
    @Autowired
    private IPlayerIncomeRepository playerIncomeRepository;

    @Override
    public Iterable<PlayerIncome> findAll() {
        return playerIncomeRepository.findAll();
    }

    @Override
    public Optional<PlayerIncome> findById(Long id) {
        return playerIncomeRepository.findById(id);
    }

    @Override
    public PlayerIncome save(PlayerIncome model) {
        return playerIncomeRepository.save(model);
    }

    @Override
    public void remove(Long id) {

    }
}
