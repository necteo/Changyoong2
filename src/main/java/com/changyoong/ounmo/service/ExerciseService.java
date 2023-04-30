package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.Exercise;
import com.changyoong.ounmo.domain.exercise.ExercisePart;
import com.changyoong.ounmo.domain.exercise.ExercisePartName;

import java.util.List;

public interface ExerciseService {
    Exercise findExerciseById(Long exerciseId);
    List<Exercise> findExercisesByPartName(ExercisePartName partName);
    Long saveExercise(String name, Boolean equipment, String img, String info, ExercisePartName... partNames);
}
