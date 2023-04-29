package com.changyoong.ounmo.domain;

import com.changyoong.ounmo.domain.exercise.ExercisePlan;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAN_ID")
    private ExercisePlan exercisePlan;

    private Long startBefore;
}
