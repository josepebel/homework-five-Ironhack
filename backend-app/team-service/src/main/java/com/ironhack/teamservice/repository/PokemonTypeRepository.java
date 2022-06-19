package com.ironhack.teamservice.repository;

import com.ironhack.teamservice.model.PokemonTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonTypeRepository extends JpaRepository<PokemonTypeEntity, Integer> {
}
