package com.repository.player;

import com.model.player.Player;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IPlayerRepository extends PagingAndSortingRepository<Player, Long> {
//    Page<Player> findPlayerByRoleContaining(String role, Pageable pageable);
//
//    Page<Player> findPlayerByNameContaining(String name, Pageable pageable);
}
