package com.repository.player;

import com.model.player.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayerRepository extends PagingAndSortingRepository<Player, Long> {
    Iterable<Player> findAllByNameContaining(String name);

    @Query(value = "select *, (salary + (salary * 0.1 * play_time) + bonus) as income from player join player_income pi on pi.id = player.player_income_id order by income desc limit 1;", nativeQuery = true)
    Player findPlayerMaxSalary();
    @Query(value = "select *, (salary + (salary * 0.1 * play_time) + bonus) as income from player join player_income pi on pi.id = player.player_income_id order by income asc ;", nativeQuery = true)
    Iterable<Player> sortPlayerSalaryAsc();

    @Query (value = "select *, (salary + (salary * 0.1 * play_time) + bonus) as income from player join player_income pi on pi.id = player.player_income_id order by income desc ;", nativeQuery = true)
    Iterable<Player> sortPlayerSalaryDesc();


    Page<Player> findPlayerByNameContaining(String name, Pageable pageable);}
