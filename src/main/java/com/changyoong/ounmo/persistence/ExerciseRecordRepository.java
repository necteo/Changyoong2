package com.changyoong.ounmo.persistence;

import com.changyoong.ounmo.domain.exercise.ExerciseRecord;
import org.springframework.data.repository.CrudRepository;

public interface ExerciseRecordRepository extends CrudRepository<ExerciseRecord, Long> {
}
