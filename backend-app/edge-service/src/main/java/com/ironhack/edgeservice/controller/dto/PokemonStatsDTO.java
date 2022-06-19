package com.ironhack.edgeservice.controller.dto;

public class PokemonStatsDTO {

    private String name;
    private short value;

    public PokemonStatsDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }
}
