package com.ironhack.trainersservice.repository;

import com.ironhack.trainersservice.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TrainerRepository extends JpaRepository<Trainer, String> {
}
