package com.ironhack.teamservice.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pokemon_type")
public class PokemonTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "pokemon_id")
    private PokemonEntity pokemon;

    private String name;

    private LocalDate creationDate;
    private LocalDate modificationDate;
    private String userCreation;
    private String userModification;

    public PokemonTypeEntity() {
    }

    public int getId() {
        return id;
    }

    public void setPokemon(PokemonEntity pokemon) {
        this.pokemon = pokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setModificationDate(LocalDate modificationDate) {
        this.modificationDate = modificationDate;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }

    public void setUserModification(String userModification) {
        this.userModification = userModification;
    }
}
