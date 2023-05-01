package com.changyoong.ounmo.controller;

import com.changyoong.ounmo.domain.exercise.*;
import com.changyoong.ounmo.service.ExercisePlanService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ounmo/plan")
public class ExercisePlanController {
    private final ExercisePlanService exercisePlanService;

    @PostMapping("/all")
    public List<ExercisePlan> findAllExercise() {
        System.out.println("find all plan");
        return exercisePlanService.findAll();
    }

    @GetMapping("/recommend")
    public List<Exercise> recommendExercises(@RequestParam("equipment") Boolean equipment,
                                             @RequestParam("part-name") String partName) {
        return exercisePlanService.recommendExercise(equipment, ExercisePartName.valueOf(partName));
    }

    @PostMapping("/save")
    public Long savePlan(@RequestBody ExercisePlanDTO planDTO) {
        return exercisePlanService.savePlan(planDTO);
    }

    @PostMapping("/add-exercise/{planId}")
    public Long addExercise(@PathVariable("planId") Long planId, @RequestBody PlannedExerciseData plannedExerciseData) {
        System.out.println("add one exercise to plan");
        return exercisePlanService.addExercise(planId, plannedExerciseData);
    }
}
