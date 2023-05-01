package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.Exercise;
import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ExerciseServiceTest {
    @Autowired
    ExerciseService exerciseService;

    @Test
    void findExercisesByPartName() throws Exception {
        List<Exercise> exercisesByPartName = exerciseService.findExercisesByPartName(ExercisePartName.CHEST);

        assertThat(exercisesByPartName.get(0).getName() + " " + exercisesByPartName.get(1).getName())
                .as("푸쉬업과 턱걸이를 조회해야함")
                .isEqualTo("푸쉬업 턱걸이");
    }

    @Test
    public void saveExercise() throws Exception {
        Long exerciseId = exerciseService.saveExercise("푸쉬업", false,
                "http://localhost:80/images/push-up.jpg", "대충 방법과 주의사항",
                ExercisePartName.ARM, ExercisePartName.CHEST);

        Exercise findExercise = exerciseService.findExerciseById(exerciseId);

        assertThat(findExercise.getId())
                .as("저장한 운동과 조회된 운동이 같아야 함")
                .isEqualTo(exerciseId);

        exerciseService.saveExercise("턱걸이", false,
                "http://localhost:80/images/pull-up.jpg", "대충 방법과 주의사항",
                ExercisePartName.BACK, ExercisePartName.ARM,
                ExercisePartName.CHEST, ExercisePartName.SHOULDER);
    }

    @Test
    @AfterEach
    void findAll() {
        List<Exercise> all = exerciseService.findAll();
        assertThat(all.size())
                .as("크기가 같아야함 ")
                .isEqualTo(2);
    }
}