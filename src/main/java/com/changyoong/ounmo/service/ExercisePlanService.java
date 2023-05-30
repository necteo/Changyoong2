package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.*;
import com.changyoong.ounmo.dto.ExerciseDTO;
import com.changyoong.ounmo.dto.ExercisePlanDTO;
import com.changyoong.ounmo.dto.PlannedExerciseDTO;

import java.util.List;

public interface ExercisePlanService {
    List<ExercisePlanDTO> findAll();
    ExercisePlanDTO findPlanById(Long planId);
    Long savePlan(ExercisePlanDTO planDTO);
    Long addExercise(Long planId, PlannedExerciseDTO plannedExerciseDTO);
    List<ExerciseDTO> recommendExercise(Boolean isEquipment, ExercisePartName partName);
    Long modifyPlan(ExercisePlanDTO planDTO);
    Long removePlan(Long planId);
}
