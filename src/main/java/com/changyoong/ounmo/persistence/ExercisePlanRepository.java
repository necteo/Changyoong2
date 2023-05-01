package com.changyoong.ounmo.persistence;

import com.changyoong.ounmo.domain.exercise.ExercisePlan;

import java.util.List;

public interface ExercisePlanRepository extends BaseRepository<ExercisePlan, Long> {
    List<ExercisePlan> findAll();
}
