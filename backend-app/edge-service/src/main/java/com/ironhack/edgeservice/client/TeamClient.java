package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.controller.dto.PokemonDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient("teams-service")
public interface TeamClient {

    @GetMapping("/pokemons/{name}")
    List<PokemonDTO> getAll(@PathVariable(name = "name") String name);

    @PostMapping("/pokemons/{name}")
    PokemonDTO store(@PathVariable(name = "name") String name, @RequestBody PokemonDTO pokemonDTO);

    @DeleteMapping("/pokemons/{id}")
    void delete(@PathVariable(name = "id") int id);

}
