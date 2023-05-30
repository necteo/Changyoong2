package com.changyoong.ounmo.mapper;

import com.changyoong.ounmo.domain.exercise.ExerciseRecord;
import com.changyoong.ounmo.dto.ConductExerciseRecordDTO;
import com.changyoong.ounmo.dto.ExerciseRecordDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ExerciseRecordMapper {
    ExerciseRecordMapper INSTANCE = Mappers.getMapper(ExerciseRecordMapper.class);

    default ExerciseRecordDTO toExerciseRecordDTO(ExerciseRecord record) {
        if (record == null)
            return null;

        List<ConductExerciseRecordDTO> conductExerciseRecordDTOList = new ArrayList<>();
        record.getConductExerciseRecords().forEach(conductExerciseRecord -> {
            ConductExerciseRecordDTO conductExerciseRecordDTO = ConductExerciseRecordDTO.builder()
                    .id(conductExerciseRecord.getId())
                    .sets(conductExerciseRecord.getSets())
                    .count(conductExerciseRecord.getCount())
                    .isExercised(conductExerciseRecord.getIsExercised())
                    .exerciseId(conductExerciseRecord.getExercise().getId())
                    .build();
            conductExerciseRecordDTOList.add(conductExerciseRecordDTO);
        });

        return ExerciseRecordDTO.builder()
                .id(record.getId())
                .date(record.getDate())
                .time(record.getTime())
                .calories(record.getCalories())
                .userNum(record.getUser().getNum())
                .conductExerciseRecordDTOList(conductExerciseRecordDTOList)
                .build();
    }

    default List<ExerciseRecordDTO> toExerciseRecordDTOList(List<ExerciseRecord> exerciseRecords) {
        List<ExerciseRecordDTO> exerciseRecordDTOList = new ArrayList<>();
        exerciseRecords.forEach(exerciseRecord -> {
            exerciseRecordDTOList.add(ExerciseRecordMapper.INSTANCE.toExerciseRecordDTO(exerciseRecord));
        });
        return exerciseRecordDTOList;
    }
}
