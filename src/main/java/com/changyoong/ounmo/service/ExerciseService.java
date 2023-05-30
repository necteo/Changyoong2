package com.changyoong.ounmo.service;

import com.changyoong.ounmo.dto.ExerciseDTO;

import java.util.List;

public interface ExerciseService {
    List<ExerciseDTO> findAll();
    ExerciseDTO findExerciseById(Long exerciseId);
    List<ExerciseDTO> findExercisesByName(String name);
    Long saveExercise(ExerciseDTO exerciseDTO);
}
