package com.ironhack.teamservice.controller.dto;

import java.util.List;

public class PokemonDTO {

    private int id;
    private String name;
    private String imageUrl;
    private List<String> typeList;
    private List<PokemonStatsDTO> statsList;

    public PokemonDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<String> getTypeList() {
        return typeList;
    }

    public void setTypeList(List<String> typeList) {
        this.typeList = typeList;
    }

    public List<PokemonStatsDTO> getStatsList() {
        return statsList;
    }

    public void setStatsList(List<PokemonStatsDTO> statsList) {
        this.statsList = statsList;
    }
}
