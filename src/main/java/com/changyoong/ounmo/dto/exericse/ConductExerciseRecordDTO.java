package com.changyoong.ounmo.dto.exericse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConductExerciseRecordDTO {
    private Long id;
    private Long sets;
    private Long count;
    private Boolean isExercised;
    private Long exerciseId;
}
