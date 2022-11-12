package com.service.player;

import com.model.player.Performance;
import com.model.player.Player;
import com.model.player.Position;
import com.model.player.Status;
import com.model.trainer.Trainer;
import com.service.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPlayerService extends IGeneralService<Player> {
    Iterable<Position> findAllPosition();

    Iterable<Performance> findAllPerformance();

    Iterable<Status> findAllStatus();

    Iterable<Player> findAllByName(String name);

    Page<Player> findPage(Pageable pageable);

    Page<Player> findPlayerByNameContaining(String name, Pageable pageable);

    Page<Player> sortPlayerSalaryDesc(Pageable pageable);

    Page<Player> sortPlayerSalaryAsc(Pageable pageable);

    Page<Player> findPlayerByPositionContaining(String position, Pageable pageable);

    Page<Player> findPlayerByStatusContaining(String status, Pageable pageable);

    Player findPlayerMaxSalary();
    Page<Player> findAllPage(Pageable pageable);
}
