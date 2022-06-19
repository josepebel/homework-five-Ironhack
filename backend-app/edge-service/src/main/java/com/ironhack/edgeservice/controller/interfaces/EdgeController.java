package com.ironhack.edgeservice.controller.interfaces;

import com.ironhack.edgeservice.controller.dto.PokemonDTO;
import com.ironhack.edgeservice.controller.dto.TrainerDTO;

import java.util.List;

public interface EdgeController {

    List<PokemonDTO> getAll(String name);
    PokemonDTO store(String name, PokemonDTO pokemonDTO);
    void delete(int id);

    TrainerDTO addTrainer(TrainerDTO trainer);
    List<TrainerDTO> showTrainers();
    TrainerDTO getTrainer(String name);
    void deleteTrainer(String name);
}
