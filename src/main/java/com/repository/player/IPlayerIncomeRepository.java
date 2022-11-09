package com.repository.player;

import com.model.player.Performance;
import com.model.player.PlayerIncome;
import org.springframework.data.repository.CrudRepository;

public interface IPlayerIncomeRepository extends CrudRepository<PlayerIncome,Long> {
}
