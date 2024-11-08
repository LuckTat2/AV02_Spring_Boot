package com.luck.todosimple.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private Double entryFee;

    @NotNull
    private Double prizePool;

    @OneToMany(mappedBy = "tournament", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Player> players;

    @NotNull
    private Boolean reentryAllowed;

    public Tournament() {}

    public Tournament(String name, Double entryFee, Double prizePool, Boolean reentryAllowed) {
        this.name = name;
        this.entryFee = entryFee;
        this.prizePool = prizePool;
        this.reentryAllowed = reentryAllowed;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Double getEntryFee() { return entryFee; }
    public void setEntryFee(Double entryFee) { this.entryFee = entryFee; }
    public Double getPrizePool() { return prizePool; }
    public void setPrizePool(Double prizePool) { this.prizePool = prizePool; }
    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }
    public Boolean getReentryAllowed() { return reentryAllowed; }
    public void setReentryAllowed(Boolean reentryAllowed) { this.reentryAllowed = reentryAllowed; }
}
