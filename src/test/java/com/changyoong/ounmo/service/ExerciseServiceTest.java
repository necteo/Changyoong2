package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import com.changyoong.ounmo.dto.ExerciseDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExerciseServiceTest {
    @Autowired
    ExerciseService exerciseService;

    @Test
    public void saveExercise() throws Exception {
        ExerciseDTO exerciseDTO = ExerciseDTO.builder()
                .name("푸쉬업")
                .isEquipment(false)
                .img("http://localhost:80/images/push-up.jpg")
                .info("대충 방법과 주의사항")
                .parts(new ArrayList<>(List.of(ExercisePartName.ARM, ExercisePartName.CHEST)))
                .build();
        Long exerciseId = exerciseService.saveExercise(exerciseDTO);

        ExerciseDTO findExercise = exerciseService.findExerciseById(exerciseId);

        assertThat(findExercise.getId())
                .as("저장한 운동과 조회된 운동이 같아야 함")
                .isEqualTo(exerciseId);
    }

    @Test
    @AfterEach
    void findAll() {
        List<ExerciseDTO> all = exerciseService.findAll();
        assertThat(all.size())
                .as("크기가 같아야함 ")
                .isEqualTo(2);
    }
}