package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.ConductExerciseRecord;
import com.changyoong.ounmo.domain.exercise.Exercise;
import com.changyoong.ounmo.domain.exercise.ExerciseRecord;
import com.changyoong.ounmo.domain.user.User;
import com.changyoong.ounmo.dto.ExerciseRecordDTO;
import com.changyoong.ounmo.mapper.ExerciseRecordMapper;
import com.changyoong.ounmo.repository.ConductExerciseRecordRepository;
import com.changyoong.ounmo.repository.ExerciseRecordRepository;
import com.changyoong.ounmo.repository.ExerciseRepository;
import com.changyoong.ounmo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ExerciseRecordServiceImpl implements ExerciseRecordService {
    private final ExerciseRecordRepository exerciseRecordRepository;
    private final ConductExerciseRecordRepository conductExerciseRecordRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    @Override
    public ExerciseRecordDTO findById(Long recordId) {
        ExerciseRecord exerciseRecord = exerciseRecordRepository.findById(recordId)
                .orElseThrow(() -> new IllegalArgumentException("record doesn't exist"));
        return ExerciseRecordMapper.INSTANCE.toExerciseRecordDTO(exerciseRecord);
    }

    @Override
    public List<ExerciseRecordDTO> findAll() {
        List<ExerciseRecord> exerciseRecords = (List<ExerciseRecord>) exerciseRecordRepository.findAll();
        return ExerciseRecordMapper.INSTANCE.toExerciseRecordDTOList(exerciseRecords);
    }

    @Override
    public Long saveRecord(ExerciseRecordDTO exerciseRecordDTO) {
        User user = userRepository.findById(exerciseRecordDTO.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));

        List<ConductExerciseRecord> conductExerciseRecords = new ArrayList<>();
        exerciseRecordDTO.getConductExerciseRecordDTOList().forEach(conductExerciseRecordDTO -> {
            Exercise exercise = exerciseRepository.findById(conductExerciseRecordDTO.getExerciseId())
                    .orElseThrow(() -> new IllegalArgumentException("exercise doesn't exist"));
            conductExerciseRecords.add(ConductExerciseRecord.createConductExerciseRecord(
                    conductExerciseRecordDTO.getSets(),
                    conductExerciseRecordDTO.getCount(),
                    conductExerciseRecordDTO.getIsExercised(),
                    exercise
            ));
        });

        ExerciseRecord exerciseRecord = ExerciseRecord.createExerciseRecord(exerciseRecordDTO.getDate(),
                exerciseRecordDTO.getTime(), exerciseRecordDTO.getCalories(), user, conductExerciseRecords);
        exerciseRecordRepository.save(exerciseRecord);
        conductExerciseRecordRepository.saveAll(conductExerciseRecords);
        return exerciseRecord.getId();
    }
}
