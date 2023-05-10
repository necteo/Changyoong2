package com.changyoong.ounmo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlannedExerciseDTO {
    private Long exerciseId;
    private Long sets;
    private Long count;
}
