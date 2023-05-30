package com.changyoong.ounmo.service;

import com.changyoong.ounmo.dto.ExerciseRecordDTO;

import java.util.List;

public interface ExerciseRecordService {
    ExerciseRecordDTO findById(Long recordId);
    List<ExerciseRecordDTO> findAll();
    Long saveRecord(ExerciseRecordDTO exerciseRecordDTO);
}
