package com.changyoong.ounmo.domain.exercise;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Boolean isEquipment;
    private String img;
    private String info;

    @OneToMany(mappedBy = "exercise")
    private List<ExercisePart> parts = new ArrayList<>();

    @OneToMany(mappedBy = "exercise")
    private List<ConductExerciseRecord> conductExerciseRecords = new ArrayList<>();

    public void addExercisePart(ExercisePart exercisePart) {
        parts.add(exercisePart);
        exercisePart.setExercise(this);
    }

    public static Exercise createExercise(String name, Boolean isEquipment, String img, String info,
                                          List<ExercisePart> exerciseParts) {
        Exercise exercise = new Exercise();
        exercise.setName(name);
        exercise.setIsEquipment(isEquipment);
        exercise.setImg(img);
        exercise.setInfo(info);
        exerciseParts.forEach(exercise::addExercisePart);
        return exercise;
    }
}
