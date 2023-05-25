package com.changyoong.ounmo.persistence;

import com.changyoong.ounmo.domain.exercise.ExercisePart;
import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ExercisePartRepository extends CrudRepository<ExercisePart, Long> {
    List<ExercisePart> findAllByPartName(ExercisePartName partName);
}
