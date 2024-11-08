package com.luck.todosimple.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luck.todosimple.models.Tournament;

import java.util.List;

public interface TournamentRepository extends JpaRepository<Tournament, Long> {

    // Método para buscar torneios por nome
    List<Tournament> findByName(String name);

    @Query("SELECT t FROM Tournament t JOIN t.players p JOIN Dealer d " +
       "WHERE p.id = :playerId AND d.id = :dealerId")
    List<Tournament> findByPlayerAndDealer(@Param("playerId") Long playerId, @Param("dealerId") Long dealerId);

} 