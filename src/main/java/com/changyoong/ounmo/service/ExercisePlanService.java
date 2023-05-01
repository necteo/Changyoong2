package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.*;

import java.time.LocalDateTime;
import java.util.List;

public interface ExercisePlanService {
    List<ExercisePlan> findAll();
    ExercisePlan findPlanById(Long planId);
    Long savePlan(ExercisePlanDTO planDTO);
    Long addExercise(Long planId, PlannedExerciseData plannedExerciseData);
    List<Exercise> recommendExercise(Boolean equipment, ExercisePartName partName);
}
