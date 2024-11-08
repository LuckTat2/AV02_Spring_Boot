package com.luck.todosimple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.luck.todosimple.models.Player;
import com.luck.todosimple.models.Tournament;
import com.luck.todosimple.services.PlayerService;
import com.luck.todosimple.services.TournamentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TournamentService tournamentService;

    // Criar um novo jogador e associá-lo ao torneio
    @PostMapping
    public ResponseEntity<Player> createPlayer(@RequestBody Player player, @RequestParam Long tournamentId) {
        Optional<Tournament> tournament = tournamentService.findById(tournamentId);
        if (tournament.isPresent()) {
            player.setTournament(tournament.get());
            Player createdPlayer = playerService.create(player);
            return new ResponseEntity<>(createdPlayer, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Buscar um jogador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Optional<Player> player = playerService.findById(id);
        return player.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Atualizar um jogador
    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player playerDetails) {
        Optional<Player> playerOptional = playerService.findById(id);
        if (playerOptional.isPresent()) {
            playerDetails.setId(id);
            Player updatedPlayer = playerService.update(playerDetails);
            return new ResponseEntity<>(updatedPlayer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Excluir um jogador
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Buscar jogadores por nome
    @GetMapping("/search")
    public ResponseEntity<List<Player>> getPlayersByName(@RequestParam String name) {
        List<Player> players = playerService.findByName(name);
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

    // Buscar todos os jogadoresS
    @GetMapping("/players")
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.findAll();
        return new ResponseEntity<>(players, HttpStatus.OK);
    }

}
