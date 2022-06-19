package com.ironhack.teamservice.controller.implementations;

import com.ironhack.teamservice.controller.dto.PokemonDTO;
import com.ironhack.teamservice.controller.interfaces.TeamController;
import com.ironhack.teamservice.service.interfaces.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeamControllerImpl implements TeamController {

    @Autowired
    private TeamService teamService;

    @Override
    @GetMapping("/pokemons/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<PokemonDTO> getAll(@PathVariable(name = "name") String name) {

        return teamService.getAll(name);
    }

    @Override
    @PostMapping("/pokemons/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public PokemonDTO store(@PathVariable(name = "name") String name, @RequestBody PokemonDTO pokemonDTO) {

        return teamService.store(name, pokemonDTO);
    }

    @Override
    @DeleteMapping("/pokemons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") int id) {

        teamService.delete(id);
    }
}
