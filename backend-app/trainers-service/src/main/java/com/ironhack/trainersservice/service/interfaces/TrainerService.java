package com.ironhack.trainersservice.service.interfaces;

import com.ironhack.trainersservice.controller.dto.TrainerDTO;


import java.util.List;

public interface TrainerService {

    TrainerDTO addTrainer(TrainerDTO trainer);
    List<TrainerDTO> showTrainers();
    TrainerDTO getTrainer(String name);
    void deleteTrainer(String name);

}
