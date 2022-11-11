package com.repository.player;

import com.model.player.Player;
import com.model.player.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayerRepository extends PagingAndSortingRepository<Player, Long>, JpaRepository<Player,Long> {
    Iterable<Player> findAllByNameContaining(String name);

    @Query(value = "select *, (salary + (salary * 0.1 * play_time) + bonus) as income from player join player_income pi on pi.id = player.player_income_id order by income desc limit 1;", nativeQuery = true)
    Player findPlayerMaxSalary();


    @Query(value = "select p from Player p join PlayerIncome pi on p.playerIncome.id = pi.id order by (pi.salary + (pi.salary * 0.1 * pi.playTime)) ASC")
    Page<Player> sortPlayerSalaryAsc(Pageable pageable);

    @Query(value = "select p from Player p join PlayerIncome pi on p.playerIncome.id = pi.id order by (pi.salary + (pi.salary * 0.1 * pi.playTime)) DESC")
    Page<Player> sortPlayerSalaryDesc(Pageable pageable);


    Page<Player> findPlayerByNameContaining(String name, Pageable pageable);

    @Query(value = "select p from Player p join Position po on p.position.id = po.id where po.name LIKE %:position%")
    Page<Player> findPlayerByPositionContaining (@Param("position") String position, Pageable pageable);

    @Query(value = "select p from Player p join Status s on p.status.id = s.id where s.status LIKE %:status%")
    Page<Player> findPlayerByStatusContaining (@Param("status")String status, Pageable pageable);
}
