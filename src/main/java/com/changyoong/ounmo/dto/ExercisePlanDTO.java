package com.changyoong.ounmo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExercisePlanDTO {
    private Long id;
    private Long userNum;
    private List<PlannedExerciseDTO> plannedExerciseDTOList;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String details;

    @Builder
    public ExercisePlanDTO(Long userNum,
                           List<PlannedExerciseDTO> plannedExerciseDTOList,
                           LocalDateTime startTime, LocalDateTime endTime, String details) {
        this.userNum = userNum;
        this.plannedExerciseDTOList = plannedExerciseDTOList;
        this.startTime = startTime;
        this.endTime = endTime;
        this.details = details;
    }
}
