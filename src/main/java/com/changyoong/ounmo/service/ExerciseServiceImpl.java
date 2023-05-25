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
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExercisePartRepository exercisePartRepository;

    @Override
    public List<Exercise> findAll() {
        return (List<Exercise>) exerciseRepository.findAll();
    }

    @Override
    public Exercise findExerciseById(Long exerciseId) {
        return exerciseRepository.findById(exerciseId).orElseThrow();
    }

    @Override
    public List<Exercise> findExercisesByPartName(ExercisePartName partName) {
        List<Exercise> exercises = new ArrayList<>();
        List<ExercisePart> findExerciseParts = exercisePartRepository.findAllByPartName(partName);
        if (findExerciseParts == null) throw new IllegalArgumentException("exercise parts doesn't exist");
        findExerciseParts.forEach(part -> {
            exercises.add(exerciseRepository.findById(part.getExercise().getId())
                    .orElseThrow(() -> new IllegalArgumentException("exercise doesn't exist")));
        });

        return exercises;
    }

    @Override
    public Long saveExercise(String name, Boolean equipment, String img, String info,
                             ExercisePartName... partNames) {
        List<ExercisePart> exerciseParts = new ArrayList<>();
        for (ExercisePartName partName : partNames) {
            exerciseParts.add(ExercisePart.createExercisePart(partName));
        }
        Exercise exercise = Exercise.createExercise(name, equipment, img, info, exerciseParts);
        exerciseRepository.save(exercise);
        exercisePartRepository.saveAll(exerciseParts);
        return exercise.getId();
    }
}
