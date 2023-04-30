package com.changyoong.ounmo.domain.exercise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "EXERCISE_PART")
public class ExercisePart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ExercisePartName partName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EX_ID")
    private Exercise exercise;

    public static ExercisePart createExercisePart(ExercisePartName partName) {
            ExercisePart exercisePart = new ExercisePart();
            exercisePart.setPartName(partName);
        return exercisePart;
    }
}
