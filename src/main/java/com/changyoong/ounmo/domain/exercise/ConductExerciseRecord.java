package com.changyoong.ounmo.domain.exercise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "CONDUCT_EX_RECORD")
public class ConductExerciseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sets;
    private Long count;
    private Boolean isExercised;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECORD_ID")
    private ExerciseRecord record;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EX_ID")
    private Exercise exercise;
}
