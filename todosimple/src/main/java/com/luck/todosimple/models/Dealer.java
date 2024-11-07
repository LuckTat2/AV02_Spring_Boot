package com.luck.todosimple.models;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
public class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    private Double salary;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament; // Adiciona a associação com o torneio

    // Construtor padrão
    public Dealer() {}

    // Construtor com argumentos
    public Dealer(String name, Double salary, Tournament tournament) {
        this.name = name;
        this.salary = salary;
        this.tournament = tournament;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }
}
