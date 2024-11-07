package com.luck.todosimple.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luck.todosimple.models.Tournament;
import com.luck.todosimple.repositories.TournamentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentService(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    public List<Tournament> findAll() {
        return tournamentRepository.findAll();
    }

    public Optional<Tournament> findById(Long id) {
        return tournamentRepository.findById(id);
    }

    public List<Tournament> findByName(String name) {
        return tournamentRepository.findByName(name);
    }

    @Transactional
    public Tournament create(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @Transactional
    public Tournament update(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    @Transactional
    public void delete(Long id) {
        tournamentRepository.deleteById(id);
    }
}
