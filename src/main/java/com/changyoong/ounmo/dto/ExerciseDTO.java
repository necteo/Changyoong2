package com.changyoong.ounmo.dto;

import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseDTO {
    private Long id;
    private String name;
    private Boolean isEquipment;
    private String img;
    private String info;
    private List<ExercisePartName> parts;
}
