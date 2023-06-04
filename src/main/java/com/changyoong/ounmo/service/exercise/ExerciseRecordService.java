package com.changyoong.ounmo.service.exercise;

import com.changyoong.ounmo.dto.exericse.ExerciseRecordDTO;

import java.util.List;

public interface ExerciseRecordService {
    ExerciseRecordDTO findById(Long recordId);
    List<ExerciseRecordDTO> findAll();
    Long saveRecord(ExerciseRecordDTO exerciseRecordDTO, String email);
}
