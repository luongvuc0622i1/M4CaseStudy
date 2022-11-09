package com.repository.player;

import com.model.player.Performance;
import org.springframework.data.repository.CrudRepository;

public interface IPerformanceRepository extends CrudRepository<Performance,Long> {
}
