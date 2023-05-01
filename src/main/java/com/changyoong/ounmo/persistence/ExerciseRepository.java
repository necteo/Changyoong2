package com.changyoong.ounmo.persistence;

import com.changyoong.ounmo.domain.exercise.Exercise;
import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends BaseRepository<Exercise, Long> {
    List<Exercise> findAll();
    @Query("select e from Exercise e join ExercisePart ep on e.id = ep.exercise.id " +
            "where e.equipment = :equipment and ep.partName = :partName")
    List<Exercise> findAllByEquipmentAndPart(@Param("equipment") Boolean equipment, @Param("partName") ExercisePartName partName);
}
