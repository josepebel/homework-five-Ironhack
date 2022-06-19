package com.ironhack.teamservice.controller.implementations;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class TeamControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TrainerRepository trainerRepository;

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private PokemonTypeRepository pokemonTypeRepository;

    @Autowired
    private PokemonStatsRepository pokemonStatsRepository;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private TrainerEntity trainer;
    private PokemonEntity pokemon;
    private PokemonDTO pokemonDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .build();

        trainer = new TrainerEntity();
        trainer.setName("TestTrainer");
        trainer.setAge((byte) 18);
        trainer.setHobby("hobby");
        trainer.setPhoto("");
        trainer.setCreationDate(LocalDate.now());
        trainer.setUserCreation("Testing");
        trainerRepository.save(trainer);

        pokemon = new PokemonEntity();
        pokemon.setName("bulbasaur");
        pokemon.setImageUrl("");
        pokemon.setTrainer(trainer);
        pokemon.setCreationDate(LocalDate.now());
        pokemon.setUserCreation("Testing");
        pokemonRepository.save(pokemon);

        PokemonTypeEntity pokemonType = new PokemonTypeEntity();
        pokemonType.setName("grass");
        pokemonType.setPokemon(pokemon);
        pokemonType.setCreationDate(LocalDate.now());
        pokemonTypeRepository.save(pokemonType);

        pokemonType = new PokemonTypeEntity();
        pokemonType.setName("poison");
        pokemonType.setPokemon(pokemon);
        pokemonType.setCreationDate(LocalDate.now());
        pokemonTypeRepository.save(pokemonType);

        PokemonStatsEntity pokemonStats = new PokemonStatsEntity();
        pokemonStats.setName("hp");
        pokemonStats.setValue((short) 45);
        pokemonStats.setPokemon(pokemon);
        pokemonStats.setCreationDate(LocalDate.now());
        pokemonStatsRepository.save(pokemonStats);

        pokemonStats = new PokemonStatsEntity();
        pokemonStats.setName("attack");
        pokemonStats.setValue((short) 49);
        pokemonStats.setPokemon(pokemon);
        pokemonStats.setCreationDate(LocalDate.now());
        pokemonStatsRepository.save(pokemonStats);
    }

    @AfterEach
    void tearDown() {
        pokemonStatsRepository.deleteAll();
        pokemonTypeRepository.deleteAll();
        pokemonRepository.deleteAll();
        trainerRepository.deleteAll();
    }

    @Test
    void getAll_NotFound_NoTrainerInDatabase() throws Exception {

        mockMvc.perform(get("/pokemons/InventedTrainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void getAll_ReturnOk_EmptyList() throws Exception {

        pokemonStatsRepository.deleteAll();
        pokemonTypeRepository.deleteAll();
        pokemonRepository.deleteAll();

        MvcResult mvcResult = mockMvc.perform(get("/pokemons/TestTrainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("[]"));
    }

    @Test
    void getAll_ReturnOk_TrainerWithData() throws Exception {

        MvcResult mvcResult = mockMvc.perform(get("/pokemons/TestTrainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isOk())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("bulbasaur"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("grass"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("poison"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("hp"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("45"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("attack"));
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("49"));
    }

    @Test
    void store_UnprocessedEntity_InvalidBody() throws Exception {

        pokemonDTO = new PokemonDTO();
        pokemonDTO.setName("");
        pokemonDTO.setImageUrl("");
        String body = objectMapper.writeValueAsString(pokemonDTO);
        mockMvc.perform(post("/pokemons/"+trainer.getName())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());

        pokemonDTO = new PokemonDTO();
        pokemonDTO.setName("test");
        pokemonDTO.setImageUrl("test");
        pokemonDTO.setTypeList(new ArrayList<>());
        pokemonDTO.setStatsList(new ArrayList<>());
        body = objectMapper.writeValueAsString(pokemonDTO);
        mockMvc.perform(post("/pokemons/"+trainer.getName())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void store_UnprocessedEntity_TrainerNotExitsInDatabase() throws Exception {

        pokemonDTO = new PokemonDTO();
        pokemonDTO.setName("test");
        pokemonDTO.setImageUrl("test");
        pokemonDTO.setTypeList(List.of("grass"));
        PokemonStatsDTO pokemonStatsDTO = new PokemonStatsDTO();
        pokemonStatsDTO.setName("hp");
        pokemonStatsDTO.setValue((short) 33);
        pokemonDTO.setStatsList(List.of(pokemonStatsDTO));
        String body = objectMapper.writeValueAsString(pokemonDTO);
        mockMvc.perform(post("/pokemons/InventedTrainer")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void store_IsCreated_ValidBody() throws Exception {

        pokemonDTO = new PokemonDTO();
        pokemonDTO.setName("bulbasaur");
        pokemonDTO.setImageUrl("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png");
        pokemonDTO.setTypeList(List.of("grass","poison"));
        PokemonStatsDTO pokemonStatsDTO = new PokemonStatsDTO();
        pokemonStatsDTO.setName("hp");
        pokemonStatsDTO.setValue((short) 33);
        PokemonStatsDTO pokemonStatsDTO2 = new PokemonStatsDTO();
        pokemonStatsDTO2.setName("attack");
        pokemonStatsDTO2.setValue((short) 44);
        pokemonDTO.setStatsList(List.of(pokemonStatsDTO, pokemonStatsDTO2));
        String body = objectMapper.writeValueAsString(pokemonDTO);
        MvcResult mvcResult = mockMvc.perform(post("/pokemons/"+trainer.getName())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8).contains("bulbasaur"));
        assertEquals(pokemonDTO.getName(), trainerRepository.findById(trainer.getName()).get().getPokemonList().get(0).getName());
    }

    @Test
    void store_UnprocessedEntity_TrainerWithSixPokemon() throws Exception {

        pokemon = new PokemonEntity();
        pokemon.setName("bulbasaur");
        pokemon.setImageUrl("");
        pokemon.setTrainer(trainer);
        pokemon.setCreationDate(LocalDate.now());
        pokemon.setUserCreation("Testing");
        pokemonRepository.save(pokemon);

        pokemon = new PokemonEntity();
        pokemon.setName("bulbasaur");
        pokemon.setImageUrl("");
        pokemon.setTrainer(trainer);
        pokemon.setCreationDate(LocalDate.now());
        pokemon.setUserCreation("Testing");
        pokemonRepository.save(pokemon);

        pokemon = new PokemonEntity();
        pokemon.setName("bulbasaur");
        pokemon.setImageUrl("");
        pokemon.setTrainer(trainer);
        pokemon.setCreationDate(LocalDate.now());
        pokemon.setUserCreation("Testing");
        pokemonRepository.save(pokemon);

        pokemon = new PokemonEntity();
        pokemon.setName("bulbasaur");
        pokemon.setImageUrl("");
        pokemon.setTrainer(trainer);
        pokemon.setCreationDate(LocalDate.now());
        pokemon.setUserCreation("Testing");
        pokemonRepository.save(pokemon);

        pokemon = new PokemonEntity();
        pokemon.setName("bulbasaur");
        pokemon.setImageUrl("");
        pokemon.setTrainer(trainer);
        pokemon.setCreationDate(LocalDate.now());
        pokemon.setUserCreation("Testing");
        pokemonRepository.save(pokemon);

        pokemonDTO = new PokemonDTO();
        pokemonDTO.setName("test");
        pokemonDTO.setImageUrl("test");
        pokemonDTO.setTypeList(List.of("grass"));
        PokemonStatsDTO pokemonStatsDTO = new PokemonStatsDTO();
        pokemonStatsDTO.setName("hp");
        pokemonStatsDTO.setValue((short) 33);
        pokemonDTO.setStatsList(List.of(pokemonStatsDTO));
        String body = objectMapper.writeValueAsString(pokemonDTO);
        mockMvc.perform(post("/pokemons/" +trainer.getName())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                )
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void delete_NotFound_PokemonNotExitsInDatabase() throws Exception {

        mockMvc.perform(delete("/pokemons/0")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_NoContent_PokemonExitsInDatabase() throws Exception {

        mockMvc.perform(delete("/pokemons/"+pokemon.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());
        assertEquals(Optional.empty(), pokemonRepository.findById(pokemon.getId()));
    }
}