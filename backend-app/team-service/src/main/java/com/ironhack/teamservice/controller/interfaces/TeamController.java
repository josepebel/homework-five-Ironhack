package com.ironhack.teamservice.controller.interfaces;

import com.ironhack.teamservice.controller.dto.PokemonDTO;

import java.util.List;

public interface TeamController {

    List<PokemonDTO> getAll(String name);
    PokemonDTO store(String name, PokemonDTO pokemonDTO);
    void delete(int id);
}
