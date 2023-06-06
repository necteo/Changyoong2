package com.changyoong.ounmo.controller;

import com.changyoong.ounmo.dto.exericse.ExerciseDTO;
import com.changyoong.ounmo.dto.exericse.ExerciseSearchRequestDTO;
import com.changyoong.ounmo.service.exercise.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ounmo/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @GetMapping("/all")
    public List<ExerciseDTO> findAllExercises() {
        System.out.println("find all exercises");
        return exerciseService.findAll();
    }

    @PostMapping("/search")
    public List<ExerciseDTO> findExercisesByEquipmentAndPartName(
            @RequestBody ExerciseSearchRequestDTO requestDTO
    ) {
        return exerciseService.findExercisesByEquipmentAndPartName(requestDTO);
    }
}
