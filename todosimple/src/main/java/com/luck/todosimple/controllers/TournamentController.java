package com.luck.todosimple.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tournament> getTournamentById(@PathVariable Long id) {
        Optional<Tournament> tournament = tournamentService.findById(id);
        return tournament.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.create(tournament);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tournament> updateTournament(@PathVariable Long id,
            @RequestBody Tournament tournamentDetails) {
        Optional<Tournament> existingTournament = tournamentService.findById(id);
        if (existingTournament.isPresent()) {
            Tournament tournament = existingTournament.get();
            tournament.setName(tournamentDetails.getName());
            tournament.setEntryFee(tournamentDetails.getEntryFee());
            tournament.setPrizePool(tournamentDetails.getPrizePool());
            tournament.setReentryAllowed(tournamentDetails.getReentryAllowed());
            Tournament updatedTournament = tournamentService.update(tournament);
            return ResponseEntity.ok(updatedTournament);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        tournamentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
