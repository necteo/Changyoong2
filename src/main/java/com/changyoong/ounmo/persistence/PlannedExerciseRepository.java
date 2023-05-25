package com.changyoong.ounmo.persistence;

import com.changyoong.ounmo.domain.exercise.PlannedExercise;
import org.springframework.data.repository.CrudRepository;

public interface PlannedExerciseRepository extends CrudRepository<PlannedExercise, Long> {
}
