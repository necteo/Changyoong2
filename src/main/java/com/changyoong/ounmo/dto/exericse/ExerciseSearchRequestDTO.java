package com.changyoong.ounmo.dto.exericse;

import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ExerciseSearchRequestDTO {
    private Boolean isEquipment;
    private List<ExercisePartName> partNames;
}
