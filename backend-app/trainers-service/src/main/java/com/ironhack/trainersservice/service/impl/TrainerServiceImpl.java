package com.ironhack.trainersservice.service.impl;

import com.ironhack.trainersservice.controller.dto.TrainerDTO;
import com.ironhack.trainersservice.enums.Hobby;
import com.ironhack.trainersservice.model.Trainer;
import com.ironhack.trainersservice.repository.TrainerRepository;
import com.ironhack.trainersservice.service.interfaces.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {

    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public TrainerDTO addTrainer(TrainerDTO trainerDTO) {
        Optional<Trainer> optionalTrainer = trainerRepository.findById(trainerDTO.getName());
        if (optionalTrainer.isEmpty()) {
            if(trainerDTO.getHobby() == null){
                trainerDTO.setHobby(Hobby.CUSTOM.toString());
                Trainer trainer = new Trainer(trainerDTO.getName(), trainerDTO.getAge(), Hobby.valueOf(trainerDTO.getHobby().toUpperCase()), trainerDTO.getPicture(), new Date());
                trainerRepository.save(trainer);
            } else {
            Trainer trainer = new Trainer(trainerDTO.getName(), trainerDTO.getAge(), Hobby.valueOf(trainerDTO.getHobby().toUpperCase()), trainerDTO.getPicture(), new Date());
            trainerRepository.save(trainer);
            }
            return trainerDTO;
        } throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is already a trainer with that name");
    }


    @Override
    public List<TrainerDTO> showTrainers() {
        List<Trainer> trainerList = trainerRepository.findAll();
        if (trainerList.isEmpty()) {
            return new ArrayList<>();
        }


        return trainerList.stream()
                .map(trainer -> new TrainerDTO(trainer.getName(), trainer.getAge(),
                        trainer.getHobby().toString(), trainer.getPicture()))
                .collect(Collectors.toList());
    }

    @Override
    public TrainerDTO getTrainer(String name) {
        Optional<Trainer> optionalTrainer = trainerRepository.findById(name);
        if (optionalTrainer.isPresent()) {
            return new TrainerDTO(optionalTrainer.get().getName(), optionalTrainer.get().getAge(),
                    optionalTrainer.get().getHobby().toString(), optionalTrainer.get().getPicture());
        } else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer with name " + name  + " doesn't exist");
    }

    public void deleteTrainer(String name){
        Trainer trainerToDelete = trainerRepository.findById(name).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Trainer with name " + name  + " doesn't exist"));
            trainerRepository.delete(trainerToDelete);
    }

}



