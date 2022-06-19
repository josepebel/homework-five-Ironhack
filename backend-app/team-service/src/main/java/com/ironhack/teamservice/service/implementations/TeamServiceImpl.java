package com.ironhack.teamservice.service.implementations;

import com.ironhack.teamservice.controller.dto.PokemonDTO;
import com.ironhack.teamservice.controller.dto.PokemonStatsDTO;
import com.ironhack.teamservice.model.PokemonEntity;
import com.ironhack.teamservice.model.PokemonStatsEntity;
import com.ironhack.teamservice.model.PokemonTypeEntity;
import com.ironhack.teamservice.model.TrainerEntity;
import com.ironhack.teamservice.repository.PokemonRepository;
import com.ironhack.teamservice.repository.PokemonStatsRepository;
import com.ironhack.teamservice.repository.PokemonTypeRepository;
import com.ironhack.teamservice.repository.TrainerRepository;
import com.ironhack.teamservice.service.interfaces.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private PokemonTypeRepository pokemonTypeRepository;

    @Autowired
    private PokemonStatsRepository pokemonStatsRepository;

    @Override
    public List<PokemonDTO> getAll(String name) {

        Optional<TrainerEntity> optionalTrainer = trainerRepository.findById(name);

        if (!optionalTrainer.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There are not trainer with that name");
        }

        List<PokemonEntity> pokemonList = optionalTrainer.get().getPokemonList();
        List<PokemonDTO> outputPokemonList = new ArrayList<>();

        for (PokemonEntity pokemon : pokemonList) {
            PokemonDTO pokemonDTO = new PokemonDTO();
            pokemonDTO.setId(pokemon.getId());
            pokemonDTO.setName(pokemon.getName());
            pokemonDTO.setImageUrl(pokemon.getImageUrl());

            List<String> typeList = new ArrayList<>();
            for (PokemonTypeEntity pokemonType : pokemon.getPokemonTypeList()) {
                typeList.add(pokemonType.getName());
            }
            pokemonDTO.setTypeList(typeList);

            List<PokemonStatsDTO> statsDTOList = new ArrayList<>();
            for (PokemonStatsEntity pokemonStats : pokemon.getPokemonStatsList()) {
                PokemonStatsDTO pokemonStatsDTO = new PokemonStatsDTO();
                pokemonStatsDTO.setName(pokemonStats.getName());
                pokemonStatsDTO.setValue(pokemonStats.getValue());
                statsDTOList.add(pokemonStatsDTO);
            }
            pokemonDTO.setStatsList(statsDTOList);

            outputPokemonList.add(pokemonDTO);
        }

        return outputPokemonList;
    }

    @Override
    public PokemonDTO store(String name, PokemonDTO pokemonDTO) {

        if ((pokemonDTO.getName().equals("")) || (pokemonDTO.getImageUrl().equals("")) ||
                (pokemonDTO.getTypeList().isEmpty()) || (pokemonDTO.getStatsList().isEmpty())) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Body not able to process");
        }

        Optional<TrainerEntity> optionalTrainer = trainerRepository.findById(name);
        if (!optionalTrainer.isPresent()) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Trainer does not exist");
        }

        if (optionalTrainer.get().getPokemonList().size() == 6) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "No more Pokemons can be added");
        }

        PokemonEntity pokemonEntity = new PokemonEntity();
        PokemonDTO outputPokemonDTO = new PokemonDTO();

        pokemonEntity.setName(pokemonDTO.getName());
        pokemonEntity.setImageUrl(pokemonDTO.getImageUrl());
        pokemonEntity.setTrainer(optionalTrainer.get());
        pokemonEntity.setCreationDate(LocalDate.now());
        pokemonEntity.setModificationDate(LocalDate.of(1,1,1));
        pokemonEntity.setUserCreation(name);
        pokemonEntity.setUserModification("");
        pokemonRepository.save(pokemonEntity);

        for (String pokemonType : pokemonDTO.getTypeList()) {
            PokemonTypeEntity pokemonTypeEntity = new PokemonTypeEntity();
            pokemonTypeEntity.setName(pokemonType);
            pokemonTypeEntity.setCreationDate(LocalDate.now());
            pokemonTypeEntity.setModificationDate(LocalDate.of(1,1,1));
            pokemonTypeEntity.setUserCreation(name);
            pokemonTypeEntity.setUserModification("");
            pokemonTypeEntity.setPokemon(pokemonEntity);
            pokemonTypeRepository.save(pokemonTypeEntity);
        }

        outputPokemonDTO.setTypeList(pokemonDTO.getTypeList());

        for (PokemonStatsDTO pokemonStatsDTO : pokemonDTO.getStatsList()) {
            PokemonStatsEntity pokemonStatsEntity = new PokemonStatsEntity();
            pokemonStatsEntity.setName(pokemonStatsDTO.getName());
            pokemonStatsEntity.setValue(pokemonStatsDTO.getValue());
            pokemonStatsEntity.setCreationDate(LocalDate.now());
            pokemonStatsEntity.setModificationDate(LocalDate.of(1,1,1));
            pokemonStatsEntity.setUserCreation(name);
            pokemonStatsEntity.setUserModification("");
            pokemonStatsEntity.setPokemon(pokemonEntity);
            pokemonStatsRepository.save(pokemonStatsEntity);
        }
        outputPokemonDTO.setStatsList(pokemonDTO.getStatsList());

        outputPokemonDTO.setName(pokemonDTO.getName());
        outputPokemonDTO.setImageUrl(pokemonDTO.getImageUrl());
        outputPokemonDTO.setId(pokemonEntity.getId());

        return outputPokemonDTO;
    }

    @Override
    public void delete(int id) {

        PokemonEntity pokemonEntity = pokemonRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Resource with Id " + id + " not found"));

        List<PokemonTypeEntity> pokemonTypeEntityList = pokemonEntity.getPokemonTypeList();
        for (PokemonTypeEntity pokemonTypeEntity : pokemonTypeEntityList) {
            pokemonTypeRepository.delete(pokemonTypeEntity);
        }

        List<PokemonStatsEntity> pokemonStatsEntityList = pokemonEntity.getPokemonStatsList();
        for (PokemonStatsEntity pokemonStatsEntity : pokemonStatsEntityList) {
            pokemonStatsRepository.delete(pokemonStatsEntity);
        }

        pokemonRepository.delete(pokemonEntity);
    }
}
