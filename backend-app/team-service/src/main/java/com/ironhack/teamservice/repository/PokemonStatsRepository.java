package com.ironhack.teamservice.repository;

import com.ironhack.teamservice.model.PokemonStatsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonStatsRepository extends JpaRepository<PokemonStatsEntity, Integer> {
}
