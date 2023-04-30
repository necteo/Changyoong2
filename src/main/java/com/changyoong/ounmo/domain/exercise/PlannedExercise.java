package com.changyoong.ounmo.domain.exercise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "PLANNED_EXERCISE")
public class PlannedExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sets;
    private Long count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EX_ID")
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAN_ID")
    private ExercisePlan plan;

    public static PlannedExercise create(Exercise exercise, Long sets, Long count) {
        PlannedExercise plannedExercise = new PlannedExercise();
        plannedExercise.setExercise(exercise);
        plannedExercise.setSets(sets);
        plannedExercise.setCount(count);
        return plannedExercise;
    }
}
