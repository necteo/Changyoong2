package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.Exercise;

public interface ExerciseService {
    Exercise findExerciseById(Long exerciseId);
}
