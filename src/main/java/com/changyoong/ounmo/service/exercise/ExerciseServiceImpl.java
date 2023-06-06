package com.changyoong.ounmo.service.exercise;

import com.changyoong.ounmo.domain.exercise.Exercise;
import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import com.changyoong.ounmo.dto.exericse.ExerciseDTO;
import com.changyoong.ounmo.dto.exericse.ExerciseSearchRequestDTO;
import com.changyoong.ounmo.mapper.ExerciseMapper;
import com.changyoong.ounmo.repository.exercise.ExercisePartRepository;
import com.changyoong.ounmo.repository.exercise.ExerciseRepository;
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
    public List<ExerciseDTO> findExercisesByEquipmentAndPartName(ExerciseSearchRequestDTO searchRequestDTO) {
        if (searchRequestDTO.getPartNames().size() == 1) {
            List<Exercise> exerciseResponse = exerciseRepository.findAllByEquipmentAndPart(
                    searchRequestDTO.getIsEquipment(),
                    searchRequestDTO.getPartNames().get(0)
            );
            return ExerciseMapper.INSTANCE.toExerciseDTOList(exerciseResponse);
        } else if (searchRequestDTO.getPartNames().size() == 2) {
            List<Exercise> exerciseResponse = exerciseRepository.findAllByEquipmentAndParts(
                    searchRequestDTO.getIsEquipment(),
                    searchRequestDTO.getPartNames().get(0),
                    searchRequestDTO.getPartNames().get(1)
            );
            return ExerciseMapper.INSTANCE.toExerciseDTOList(exerciseResponse);
        }
        return null;
    }

    @Override
    public Long saveExercise(ExerciseDTO exerciseDTO) {
        Exercise exercise = ExerciseMapper.INSTANCE.toExercise(exerciseDTO);
        exerciseRepository.save(exercise);
        exercisePartRepository.saveAll(exercise.getParts());
        return exercise.getId();
    }
}
