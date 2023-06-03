package com.changyoong.ounmo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseRecordDTO {
    private Long id;
    private LocalDate date;
    private Long time;
    private Long calories;
    private Long userId;
    private List<ConductExerciseRecordDTO> conductExerciseRecordDTOList;
}
