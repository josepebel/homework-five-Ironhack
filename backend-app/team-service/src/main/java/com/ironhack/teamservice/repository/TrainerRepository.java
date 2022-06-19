package com.ironhack.teamservice.repository;

import com.ironhack.teamservice.model.TrainerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainerRepository extends JpaRepository<TrainerEntity, String> {

    @Query("SELECT t FROM TrainerEntity AS t LEFT JOIN FETCH t.pokemonList p WHERE t.name= :name")
    Optional<TrainerEntity> findById(@Param("name") String name);
}
