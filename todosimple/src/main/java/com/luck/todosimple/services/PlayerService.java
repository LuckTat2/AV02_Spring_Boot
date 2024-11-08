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
    public Player update(Player playerDetails) {
        // Busca o Player atual no banco de dados
        Player player = playerRepository.findById(playerDetails.getId())
                .orElseThrow(() -> new IllegalArgumentException("Player not found with id: " + playerDetails.getId()));

        // Mantém o torneio atual se o torneio não for fornecido no playerDetails
        if (playerDetails.getTournament() == null) {
            playerDetails.setTournament(player.getTournament());
        }

        // Atualiza outros dados do Player
        player.setName(playerDetails.getName());
        player.setEmail(playerDetails.getEmail());
        player.setChips(playerDetails.getChips());
        player.setPokerChips(playerDetails.getPokerChips());

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