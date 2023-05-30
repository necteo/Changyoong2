package com.changyoong.ounmo.domain.exercise;

import com.changyoong.ounmo.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "EXERCISE_PLAN")
public class ExercisePlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_NUM")
    private User user;

    private String details;

    @OneToMany(mappedBy = "plan")
    private List<PlannedExercise> plannedExercises = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "RECORD_ID")
    private ExerciseRecord record;

    public void setUser(User user) {
        this.user = user;
        user.getPlans().add(this);
    }

    public void addPlannedExercise(PlannedExercise plannedExercise) {
        this.plannedExercises.add(plannedExercise);
        plannedExercise.setPlan(this);
    }

    public static ExercisePlan createPlan(User user,
                                          List<PlannedExercise> plannedExercises,
                                          LocalDateTime startTime, LocalDateTime endTime, String details) {
        ExercisePlan exercisePlan = new ExercisePlan();
        exercisePlan.setStartTime(startTime);
        exercisePlan.setEndTime(endTime);
        exercisePlan.setDetails(details);
        exercisePlan.setUser(user);
        plannedExercises.forEach(exercisePlan::addPlannedExercise);
        return exercisePlan;
    }
}
