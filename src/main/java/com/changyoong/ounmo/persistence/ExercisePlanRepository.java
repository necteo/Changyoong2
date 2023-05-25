package com.changyoong.ounmo.persistence;

import com.changyoong.ounmo.domain.exercise.ExercisePlan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExercisePlanRepository extends CrudRepository<ExercisePlan, Long> {
}
