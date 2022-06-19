package com.ironhack.edgeservice.client;

import com.ironhack.edgeservice.controller.dto.TrainerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@FeignClient("trainer-service")
public interface TrainersClient {

    @PostMapping("/trainers")
    TrainerDTO addTrainer(@RequestBody @Valid TrainerDTO trainer);


    @GetMapping("/trainers")
    List<TrainerDTO> showTrainers();


    @GetMapping("/trainers/{name}")
    TrainerDTO getTrainer(@PathVariable String name);

    @DeleteMapping("trainers/{name}")
    void deleteTrainer(@PathVariable String name);
}
