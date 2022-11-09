package com.repository.player;

import com.model.player.Performance;
import com.model.player.PlayerIncome;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayerIncomeRepository extends CrudRepository<PlayerIncome,Long> {
}
