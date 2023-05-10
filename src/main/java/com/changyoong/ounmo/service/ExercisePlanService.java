package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.*;
import com.changyoong.ounmo.dto.ExercisePlanDTO;
import com.changyoong.ounmo.dto.PlannedExerciseDTO;

import java.util.List;

public interface ExercisePlanService {
    List<ExercisePlan> findAll();
    ExercisePlan findPlanById(Long planId);
    Long savePlan(ExercisePlanDTO planDTO);
    Long addExercise(Long planId, PlannedExerciseDTO plannedExerciseDTO);
    List<Exercise> recommendExercise(Boolean equipment, ExercisePartName partName);
}
