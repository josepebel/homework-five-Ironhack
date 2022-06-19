package com.ironhack.edgeservice.controller.impl;

import com.ironhack.edgeservice.controller.dto.PokemonDTO;
import com.ironhack.edgeservice.controller.dto.TrainerDTO;
import com.ironhack.edgeservice.controller.interfaces.EdgeController;
import com.ironhack.edgeservice.service.interfaces.EdgeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class EdgeControllerImpl implements EdgeController {

    @Autowired
    private EdgeService edgeService;

    @GetMapping("/pokemons/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<PokemonDTO> getAll(@PathVariable(name = "name") String name) {
        return edgeService.getAll(name);
    }

    @PostMapping("/pokemons/{name}")
    @ResponseStatus(HttpStatus.CREATED)
    public PokemonDTO store(@PathVariable(name = "name") String name, @RequestBody PokemonDTO pokemonDTO) {
        return edgeService.store(name, pokemonDTO);
    }

    @DeleteMapping("/pokemons/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(name = "id") int id) {
        edgeService.delete(id);
    }

    @PostMapping("/trainers")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainerDTO addTrainer(@RequestBody @Valid TrainerDTO trainer) {
        return edgeService.addTrainer(trainer);
    }

    @GetMapping("/trainers")
    @ResponseStatus(HttpStatus.OK)
    public List<TrainerDTO> showTrainers() {
        return edgeService.showTrainers();
    }

    @GetMapping("/trainers/{name}")
    @ResponseStatus(HttpStatus.OK)
    public TrainerDTO getTrainer(@PathVariable String name) {
        return edgeService.getTrainer(name);
    }

    @DeleteMapping("trainers/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTrainer(@PathVariable String name) {
        edgeService.deleteTrainer(name);
    }


}
