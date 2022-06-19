package com.ironhack.edgeservice.service.impl;

import com.ironhack.edgeservice.client.TeamClient;
import com.ironhack.edgeservice.client.TrainersClient;
import com.ironhack.edgeservice.controller.dto.PokemonDTO;
import com.ironhack.edgeservice.controller.dto.TrainerDTO;
import com.ironhack.edgeservice.service.interfaces.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EdgeServiceImpl implements EdgeService {

    @Autowired
    private TeamClient teamClient;

    @Autowired
    private TrainersClient trainersClient;


    public List<PokemonDTO> getAll(String name) {
        return teamClient.getAll(name);
    }

    public PokemonDTO store(String name, PokemonDTO pokemonDTO) {
        return teamClient.store(name, pokemonDTO);
    }

    public void delete(int id) {
        teamClient.delete(id);
    }


    public TrainerDTO addTrainer(TrainerDTO trainer) {
        return trainersClient.addTrainer(trainer);
    }

    public List<TrainerDTO> showTrainers() {
        return trainersClient.showTrainers();
    }

    public TrainerDTO getTrainer(String name) {
        return trainersClient.getTrainer(name);
    }

    public void deleteTrainer(String name){
        List<PokemonDTO> pokemons = teamClient.getAll(name);
        if(!pokemons.isEmpty()) {
            for (PokemonDTO pokemonToDelete : pokemons) {
                teamClient.delete(pokemonToDelete.getId());
            }
        }
        trainersClient.deleteTrainer(name);}
}
