package com.luck.todosimple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.luck.todosimple.models.Dealer;
import com.luck.todosimple.models.Tournament;
import com.luck.todosimple.services.DealerService;
import com.luck.todosimple.services.TournamentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dealers")
public class DealerController {

    @Autowired
    private DealerService dealerService;

    @Autowired
    private TournamentService tournamentService; // Adicionar TournamentService aqui

    // Criar um novo dealer e associá-lo ao torneio
    @PostMapping
    public ResponseEntity<Dealer> createDealer(@RequestBody Dealer dealer, @RequestParam Long tournamentId) {
        Optional<Tournament> tournament = tournamentService.findById(tournamentId);
        if (tournament.isPresent()) {
            dealer.setTournament(tournament.get()); // Associa o dealer ao torneio
            Dealer createdDealer = dealerService.create(dealer);
            return new ResponseEntity<>(createdDealer, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Buscar todos os dealers
    @GetMapping
    public ResponseEntity<List<Dealer>> getAllDealers() {
        List<Dealer> dealers = dealerService.findAll();
        return new ResponseEntity<>(dealers, HttpStatus.OK);
    }

    // Buscar um dealer por ID
    @GetMapping("/{id}")
    public ResponseEntity<Dealer> getDealerById(@PathVariable Long id) {
        Optional<Dealer> dealer = dealerService.findById(id);
        return dealer.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Atualizar um dealer
    @PutMapping("/{id}")
    public ResponseEntity<Dealer> updateDealer(@PathVariable Long id, @RequestBody Dealer dealerDetails) {
        Optional<Dealer> dealerOptional = dealerService.findById(id);
        if (dealerOptional.isPresent()) {
            dealerDetails.setId(id);
            Dealer updatedDealer = dealerService.update(dealerDetails);
            return new ResponseEntity<>(updatedDealer, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Excluir um dealer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealer(@PathVariable Long id) {
        dealerService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
