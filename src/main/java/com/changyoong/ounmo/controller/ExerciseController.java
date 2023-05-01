package com.changyoong.ounmo.controller;

import com.changyoong.ounmo.domain.exercise.Exercise;
import com.changyoong.ounmo.service.ExerciseService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ounmo/exercise")
public class ExerciseController {
    private final ExerciseService exerciseService;

    @PostMapping("/all")
    public List<Exercise> findAllExercises() {
        System.out.println("find all exercises");
        return exerciseService.findAll();
    }
}
