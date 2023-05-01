package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.Exercise;
import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import com.changyoong.ounmo.domain.exercise.ExercisePlan;
import com.changyoong.ounmo.domain.exercise.PlannedExerciseData;

import java.time.LocalDateTime;
import java.util.List;

public interface ExercisePlanService {
    List<ExercisePlan> findAll();
    ExercisePlan findPlanById(Long planId);
    Long savePlan(Long userNum, List<PlannedExerciseData> plannedExerciseDataList,
                         LocalDateTime startTime, LocalDateTime endTime, String details);
    Long addExercise(Long planId, PlannedExerciseData plannedExerciseData);
    List<Exercise> recommendExercise(Boolean equipment, ExercisePartName partName);
}
