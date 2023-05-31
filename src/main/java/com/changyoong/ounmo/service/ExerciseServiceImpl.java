package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.Exercise;
import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import com.changyoong.ounmo.dto.ExerciseDTO;
import com.changyoong.ounmo.mapper.ExerciseMapper;
import com.changyoong.ounmo.repository.ExercisePartRepository;
import com.changyoong.ounmo.repository.ExerciseRepository;
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
    public List<ExerciseDTO> findAll() {
        List<Exercise> exercises = (List<Exercise>) exerciseRepository.findAll();
        return ExerciseMapper.INSTANCE.toExerciseDTOList(exercises);
    }

    @Override
    public ExerciseDTO findExerciseById(Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("exercise doesn't exist"));
        List<ExercisePartName> partNames = new ArrayList<>();
        exercise.getParts().forEach(exercisePart -> partNames.add(exercisePart.getPartName()));
        return ExerciseMapper.INSTANCE.toExerciseDTO(exercise, partNames);
    }

    @Override
    public List<ExerciseDTO> findExercisesByName(String name) {
        List<Exercise> exercises = exerciseRepository.findAllByNameContaining(name);
        return ExerciseMapper.INSTANCE.toExerciseDTOList(exercises);
    }

    @Override
    public Long saveExercise(ExerciseDTO exerciseDTO) {
        Exercise exercise = ExerciseMapper.INSTANCE.toExercise(exerciseDTO);
        exerciseRepository.save(exercise);
        exercisePartRepository.saveAll(exercise.getParts());
        return exercise.getId();
    }
}
