package com.repository.player;

import com.model.player.Performance;
import com.model.player.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusRepository extends CrudRepository<Status,Long> {
}
