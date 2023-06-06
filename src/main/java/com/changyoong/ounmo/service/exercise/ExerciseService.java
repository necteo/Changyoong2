package com.changyoong.ounmo.service.exercise;

import com.changyoong.ounmo.dto.exericse.ExerciseDTO;
import com.changyoong.ounmo.dto.exericse.ExerciseSearchRequestDTO;

import java.util.List;

public interface ExerciseService {
    List<ExerciseDTO> findAll();
    ExerciseDTO findExerciseById(Long exerciseId);
    List<ExerciseDTO> findExercisesByEquipmentAndPartName(ExerciseSearchRequestDTO searchRequestDTO);
    Long saveExercise(ExerciseDTO exerciseDTO);
}
