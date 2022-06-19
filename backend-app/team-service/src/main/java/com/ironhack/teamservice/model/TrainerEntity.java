package com.ironhack.teamservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="trainer")
public class TrainerEntity {

    @Id
    private String name;
    private byte age;
    private String hobby;
    private String photo;

    @OneToMany(mappedBy = "trainer")
    private List<PokemonEntity> pokemonList;

    private LocalDate creationDate;
    private LocalDate modificationDate;
    private String userCreation;
    private String userModification;

    public TrainerEntity() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public List<PokemonEntity> getPokemonList() {
        return pokemonList;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public void setUserCreation(String userCreation) {
        this.userCreation = userCreation;
    }
}
