package com.changyoong.ounmo.service.exercise;

import com.changyoong.ounmo.domain.exercise.*;
import com.changyoong.ounmo.dto.exericse.ExerciseDTO;
import com.changyoong.ounmo.dto.exericse.ExercisePlanDTO;
import com.changyoong.ounmo.dto.exericse.PlannedExerciseDTO;

import java.util.List;

public interface ExercisePlanService {
    List<ExercisePlanDTO> findAll();
    ExercisePlanDTO findPlanById(Long planId);
    Long savePlan(ExercisePlanDTO planDTO, String email);
    Long addExercise(Long planId, PlannedExerciseDTO plannedExerciseDTO);
    List<ExerciseDTO> recommendExercise(Boolean isEquipment, ExercisePartName partName);
    Long modifyPlan(ExercisePlanDTO planDTO);
    void removePlan(Long planId);
}
