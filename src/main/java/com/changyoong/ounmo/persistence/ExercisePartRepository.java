package com.changyoong.ounmo.persistence;

import com.changyoong.ounmo.domain.exercise.ExercisePart;
import com.changyoong.ounmo.domain.exercise.ExercisePartName;

import java.util.List;

public interface ExercisePartRepository extends BaseRepository<ExercisePart, Long> {
    List<ExercisePart> findAllByPartName(ExercisePartName partName);
}
