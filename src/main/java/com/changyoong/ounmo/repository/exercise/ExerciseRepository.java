package com.changyoong.ounmo.repository.exercise;

import com.changyoong.ounmo.domain.exercise.Exercise;
import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ExerciseRepository extends CrudRepository<Exercise, Long> {
    List<Exercise> findAllByNameContaining(String name);

    @Query("select e from Exercise e join ExercisePart ep on e.id = ep.exercise.id " +
            "where e.isEquipment = :isEquipment and ep.partName = :partName")
    List<Exercise> findAllByEquipmentAndPart(
            @Param("isEquipment") Boolean isEquipment,
            @Param("partName") ExercisePartName partName
    );

    @Query("select e from Exercise e join ExercisePart ep on e.id = ep.exercise.id " +
            "where e.isEquipment = :isEquipment and ep.partName = :partName1 and ep.partName = :partName2")
    List<Exercise> findAllByEquipmentAndParts(
            @Param("isEquipment") Boolean isEquipment,
            @Param("partName1") ExercisePartName partName1,
            @Param("partName2") ExercisePartName partName2
    );
}
