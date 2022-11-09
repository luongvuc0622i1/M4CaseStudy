package com.repository.player;

import com.model.player.Performance;
import com.model.player.Position;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPositionRepository extends CrudRepository<Position, Long> {
}
