package com.repository.player;

import com.model.player.Performance;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPerformanceRepository extends CrudRepository<Performance,Long> {
}
