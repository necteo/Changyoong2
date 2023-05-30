package com.changyoong.ounmo.mapper;

import com.changyoong.ounmo.domain.exercise.Exercise;
import com.changyoong.ounmo.domain.exercise.ExercisePart;
import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import com.changyoong.ounmo.dto.ExerciseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ExerciseMapper {
    ExerciseMapper INSTANCE = Mappers.getMapper(ExerciseMapper.class);

    default ExerciseDTO toExerciseDTO(Exercise exercise, List<ExercisePartName> partNames) {
        if (exercise == null)
            return null;

        ExerciseDTO.ExerciseDTOBuilder exerciseDTO = ExerciseDTO.builder();

        exerciseDTO.id(exercise.getId());
        exerciseDTO.name(exercise.getName());
        exerciseDTO.isEquipment(exercise.getIsEquipment());
        exerciseDTO.img(exercise.getImg());
        exerciseDTO.info(exercise.getInfo());
        exerciseDTO.parts(partNames);
        return exerciseDTO.build();
    }

    default Exercise toExercise(ExerciseDTO exerciseDTO) {
        if (exerciseDTO == null)
            return null;

        List<ExercisePart> exerciseParts = new ArrayList<>();
        exerciseDTO.getParts().forEach(partName -> exerciseParts.add(ExercisePart.createExercisePart(partName)));
        return Exercise.createExercise(
                exerciseDTO.getName(),
                exerciseDTO.getIsEquipment(),
                exerciseDTO.getImg(),
                exerciseDTO.getInfo(),
                exerciseParts
        );
    }

    default List<ExerciseDTO> toExerciseDTOList(List<Exercise> exercises) {
        if (exercises == null)
            return null;

        List<ExerciseDTO> exerciseDTOList = new ArrayList<>();
        exercises.forEach(exercise -> {
            List<ExercisePartName> partNames = new ArrayList<>();
            exercise.getParts().forEach(exercisePart -> partNames.add(exercisePart.getPartName()));
            exerciseDTOList.add(ExerciseMapper.INSTANCE.toExerciseDTO(exercise, partNames));
        });
        return exerciseDTOList;
    }
}
