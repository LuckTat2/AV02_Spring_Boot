package com.luck.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luck.todosimple.models.Tournament;

import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    // Método para buscar torneios por nome
    List<Tournament> findByName(String name);
}
