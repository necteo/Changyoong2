package com.changyoong.ounmo.controller;

import com.changyoong.ounmo.domain.exercise.*;
import com.changyoong.ounmo.dto.exericse.ExerciseDTO;
import com.changyoong.ounmo.dto.exericse.ExercisePlanDTO;
import com.changyoong.ounmo.dto.exericse.PlannedExerciseDTO;
import com.changyoong.ounmo.service.exercise.ExercisePlanService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/ounmo/plan")
public class ExercisePlanController {
    private final ExercisePlanService exercisePlanService;

    @GetMapping("/all")
    public List<ExercisePlanDTO> findAllExercisePlan() {
        System.out.println("find all plan");
        return exercisePlanService.findAll();
    }

    @GetMapping("/recommend")
    public List<ExerciseDTO> recommendExercises(@RequestParam("isEquipment") Boolean isEquipment,
                                                @RequestParam("part-name") String partName) {
        return exercisePlanService.recommendExercise(isEquipment, ExercisePartName.valueOf(partName));
    }

    @PostMapping("/save")
    public @ResponseBody Long savePlan(@RequestBody ExercisePlanDTO planDTO) {
        return exercisePlanService.savePlan(planDTO, "");
    }

    @PostMapping("/add-exercise/{planId}")
    public @ResponseBody Long addExercise(@PathVariable("planId") Long planId, @RequestBody PlannedExerciseDTO plannedExerciseDTO) {
        System.out.println("add one exercise to plan");
        return exercisePlanService.addExercise(planId, plannedExerciseDTO);
    }

    @PostMapping("/modify")
    public @ResponseBody Long modifyPlan(@RequestBody ExercisePlanDTO planDTO) {
        return exercisePlanService.modifyPlan(planDTO);
    }

    @GetMapping("/remove/{planId}")
    public @ResponseBody Long removePlan(@PathVariable Long planId) {
        exercisePlanService.removePlan(planId);
        return planId;
    }
}
