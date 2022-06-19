package com.ironhack.trainersservice.controller.interfaces;

import com.ironhack.trainersservice.controller.dto.TrainerDTO;

import java.util.List;


public interface TrainerController {

    TrainerDTO addTrainer(TrainerDTO trainer);
    List<TrainerDTO> showTrainers();
    TrainerDTO getTrainer(String name);
    void deleteTrainer(String name);
}
