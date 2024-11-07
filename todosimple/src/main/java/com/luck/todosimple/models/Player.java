package com.luck.todosimple.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Email
    @Size(max = 255)
    private String email;

    @NotNull
    private Double chips;

    @NotNull
    private Integer pokerChips;

    @ManyToMany(mappedBy = "players")
    private List<Tournament> tournaments;

    // Adiciona a associação com o torneio
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    // Construtor padrão
    public Player() {
    }

    // Construtor com argumentos
    public Player(String name, String email, Double chips, Integer pokerChips) {
        this.name = name;
        this.email = email;
        this.chips = chips;
        this.pokerChips = pokerChips;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getChips() {
        return chips;
    }

    public void setChips(Double chips) {
        this.chips = chips;
    }

    public Integer getPokerChips() {
        return pokerChips;
    }

    public void setPokerChips(Integer pokerChips) {
        this.pokerChips = pokerChips;
    }

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
    
    public Tournament getTournament() {
        return tournament;
    }
}
