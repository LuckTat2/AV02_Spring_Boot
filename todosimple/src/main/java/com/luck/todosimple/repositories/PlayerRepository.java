package com.luck.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luck.todosimple.models.Player;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findByName(String name);
}