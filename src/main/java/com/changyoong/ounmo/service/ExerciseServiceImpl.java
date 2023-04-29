package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.Exercise;
import com.changyoong.ounmo.domain.exercise.ExercisePart;
import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import com.changyoong.ounmo.persistence.ExercisePartRepository;
import com.changyoong.ounmo.persistence.ExerciseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExercisePartRepository exercisePartRepository;

    @Override
    public Exercise findExerciseById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId).orElseThrow();
    }

    public Long createExercise(String name, Boolean equipment, String img, String info, ExercisePartName... partName) {
        List<ExercisePart> exerciseParts = new ArrayList<>();
        for (ExercisePartName n : partName) {
            exerciseParts.add(ExercisePart.createExercisePart(n));
        }
        Exercise exercise = Exercise.createExercise(name, equipment, img, info, exerciseParts);
        exerciseRepository.save(exercise);
        exerciseParts.forEach(exercisePartRepository::save);
        return exercise.getId();
    }
}
