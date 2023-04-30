package com.changyoong.ounmo.domain.exercise;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PlannedExerciseData {
    private Long exerciseId;
    private Long sets;
    private Long count;
}
