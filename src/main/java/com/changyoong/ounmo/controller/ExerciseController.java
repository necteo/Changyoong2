package com.changyoong.ounmo.controller;

import com.changyoong.ounmo.dto.ExerciseDTO;
import com.changyoong.ounmo.service.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
