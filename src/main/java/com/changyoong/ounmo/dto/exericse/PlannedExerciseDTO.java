package com.changyoong.ounmo.dto.exericse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlannedExerciseDTO {
    private Long id;
    private Long sets;
    private Long count;
    private Long exerciseId;
}
