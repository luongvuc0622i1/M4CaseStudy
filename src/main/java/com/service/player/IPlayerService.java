package com.service.player;

import com.model.player.Performance;
import com.model.player.Player;
import com.model.player.Position;
import com.model.player.Status;
import com.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPlayerService extends IGeneralService<Player> {
    Iterable<Position> findAllPosition();

    Iterable<Performance> findAllPerformance();

    Iterable<Status> findAllStatus();

    Iterable<Player> findAllByName(String name);

    Page<Player> findPage(Pageable pageable);
}
