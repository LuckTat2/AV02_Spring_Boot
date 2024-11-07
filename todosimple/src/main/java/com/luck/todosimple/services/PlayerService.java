package com.luck.todosimple.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luck.todosimple.models.Player;
import com.luck.todosimple.repositories.PlayerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    @Transactional(readOnly = true)
    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    @Transactional
    public Player create(Player player) {
        return playerRepository.save(player);
    }

    @Transactional
    public Player update(Player player) {
        return playerRepository.save(player);
    }

    @Transactional
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Player> findByName(String name) {
        return playerRepository.findByName(name);
    }
}
