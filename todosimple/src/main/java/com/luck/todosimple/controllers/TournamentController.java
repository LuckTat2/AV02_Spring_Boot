package com.luck.todosimple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.luck.todosimple.models.Tournament;
import com.luck.todosimple.services.TournamentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    // Criar um novo torneio
    @PostMapping
    public ResponseEntity<Tournament> createTournament(@RequestBody Tournament tournament) {
        Tournament createdTournament = tournamentService.create(tournament);
        return new ResponseEntity<>(createdTournament, HttpStatus.CREATED);
    }

    // Buscar todos os torneios
    @GetMapping
    public ResponseEntity<List<Tournament>> getAllTournaments() {
        List<Tournament> tournaments = tournamentService.findAll();
        return new ResponseEntity<>(tournaments, HttpStatus.OK);
    }

    // Buscar um torneio por ID
    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        Optional<Tournament> tournament = tournamentService.findById(id);
        return tournament.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                         .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Atualizar um torneio
    @PutMapping("/{id}")
    public ResponseEntity<Tournament> updateTournament(@PathVariable Long id, @RequestBody Tournament tournamentDetails) {
        Optional<Tournament> tournamentOptional = tournamentService.findById(id);
        if (tournamentOptional.isPresent()) {
            tournamentDetails.setId(id);
            Tournament updatedTournament = tournamentService.update(tournamentDetails);
            return new ResponseEntity<>(updatedTournament, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Excluir um torneio
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        tournamentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Buscar torneios por Player e Dealer
    @GetMapping("/byPlayerAndDealer")
    public ResponseEntity<List<Tournament>> getTournamentsByPlayerAndDealer(
        @RequestParam Long playerId,
        @RequestParam Long dealerId) {
    
        List<Tournament> tournaments = tournamentService.findByPlayerAndDealer(playerId, dealerId);
        return new ResponseEntity<>(tournaments, HttpStatus.OK);
    }
}
