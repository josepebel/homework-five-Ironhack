package com.ironhack.teamservice.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pokemon")
public class PokemonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "trainer_name")
    private TrainerEntity trainer;

    @OneToMany(mappedBy = "pokemon")
    private List<PokemonTypeEntity> pokemonTypeList;

    @OneToMany(mappedBy = "pokemon")
    private List<PokemonStatsEntity> pokemonStatsList;

    private LocalDate creationDate;
    private LocalDate modificationDate;
    private String userCreation;
    private String userModification;

    public PokemonEntity() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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

    public void setTrainer(TrainerEntity trainer) {
        this.trainer = trainer;
    }

    public List<PokemonTypeEntity> getPokemonTypeList() {
        return pokemonTypeList;
    }

    public List<PokemonStatsEntity> getPokemonStatsList() {
        return pokemonStatsList;
    }

}
