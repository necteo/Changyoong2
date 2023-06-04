package com.changyoong.ounmo.mapper;

import com.changyoong.ounmo.domain.exercise.ExercisePlan;
import com.changyoong.ounmo.dto.exericse.ExercisePlanDTO;
import com.changyoong.ounmo.dto.exericse.PlannedExerciseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ExercisePlanMapper {
    ExercisePlanMapper INSTANCE = Mappers.getMapper(ExercisePlanMapper.class);

    default ExercisePlanDTO toExercisePlanDTO(ExercisePlan exercisePlan) {
        if (exercisePlan == null)
            return null;

        List<PlannedExerciseDTO> plannedExerciseDTOList = new ArrayList<>();
        exercisePlan.getPlannedExercises().forEach(plannedExercise -> {
            PlannedExerciseDTO plannedExerciseDTO = PlannedExerciseDTO.builder()
                    .id(plannedExercise.getId())
                    .sets(plannedExercise.getSets())
                    .count(plannedExercise.getCount())
                    .exerciseId(plannedExercise.getExercise().getId())
                    .build();
            plannedExerciseDTOList.add(plannedExerciseDTO);
        });

        return ExercisePlanDTO.builder()
                .id(exercisePlan.getId())
                .startTime(exercisePlan.getStartTime())
                .endTime(exercisePlan.getEndTime())
                .userId(exercisePlan.getUser().getId())
                .details(exercisePlan.getDetails())
                .plannedExerciseDTOList(plannedExerciseDTOList)
                .build();
    }

    default List<ExercisePlanDTO> toExercisePlanDTOList(List<ExercisePlan> exercisePlans) {
        List<ExercisePlanDTO> exercisePlanDTOList = new ArrayList<>();
        exercisePlans.forEach(exercisePlan ->
                exercisePlanDTOList.add(ExercisePlanMapper.INSTANCE.toExercisePlanDTO(exercisePlan)));
        return exercisePlanDTOList;
    }
}
