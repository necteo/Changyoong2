package com.changyoong.ounmo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExercisePlanDTO {
    private Long userNum;
    private List<PlannedExerciseDTO> plannedExerciseDTOList;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String details;
}
