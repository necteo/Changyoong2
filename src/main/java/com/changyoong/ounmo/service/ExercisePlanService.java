package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.Exercise;
import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import com.changyoong.ounmo.domain.exercise.ExercisePlan;
import com.changyoong.ounmo.domain.exercise.PlannedExerciseData;
import com.changyoong.ounmo.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

public interface ExercisePlanService {
    public ExercisePlan findPlanById(Long planId);
    public Long savePlan(Long userNum, List<PlannedExerciseData> plannedExerciseDataList,
                         LocalDateTime startTime, LocalDateTime endTime, String details);
    public List<Exercise> recommendExercise(Boolean equipment, ExercisePartName partName);
}
