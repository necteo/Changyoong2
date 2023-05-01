package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.Exercise;
import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import com.changyoong.ounmo.domain.exercise.ExercisePlanDTO;
import com.changyoong.ounmo.domain.exercise.PlannedExerciseData;
import com.changyoong.ounmo.domain.user.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class ExercisePlanServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    ExerciseService exerciseService;
    @Autowired
    ExercisePlanService exercisePlanService;

    @Test
    void recommendExercise() {
        exerciseService.saveExercise("푸쉬업", false,
                "http://localhost:80/images/push-up.jpg", "대충 방법과 주의사항",
                ExercisePartName.ARM, ExercisePartName.CHEST);

        Long exerciseId2 = exerciseService.saveExercise("턱걸이", true,
                "http://localhost:80/images/pull-up.jpg", "대충 방법과 주의사항",
                ExercisePartName.BACK, ExercisePartName.ARM,
                ExercisePartName.CHEST, ExercisePartName.SHOULDER);

        List<Exercise> recommendedExercises =
                exercisePlanService.recommendExercise(true, ExercisePartName.ARM);

        recommendedExercises.forEach(exercise -> assertThat(exercise.getId())
                .as("추천 운동은 턱걸이여야 함")
                .isEqualTo(exerciseId2));
    }

    @Test
    void savePlan() {
        User user = new User();
        user.setName("kim");
        user.setId("kim12");
        user.setPw("qwer12");
        user.setBirth(LocalDate.now());
        user.setHeight(170);
        user.setWeight(60);
        user.setGender("남자");
        Long userNum = userService.saveUser(user);

        Long exerciseId = exerciseService.saveExercise("푸쉬업", false,
                "http://localhost:80/images/push-up.jpg", "대충 방법과 주의사항",
                ExercisePartName.ARM, ExercisePartName.CHEST);

        Long exerciseId2 = exerciseService.saveExercise("턱걸이", true,
                "http://localhost:80/images/pull-up.jpg", "대충 방법과 주의사항",
                ExercisePartName.BACK, ExercisePartName.ARM,
                ExercisePartName.CHEST, ExercisePartName.SHOULDER);

        LocalDateTime startTime = LocalDateTime.of(2023, 4, 30, 20, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 4, 30, 21, 30);
        String details = "어떤 계획인지 설명";
        List<PlannedExerciseData> plannedExerciseDataList = new ArrayList<>();
        plannedExerciseDataList.add(new PlannedExerciseData(exerciseId, 3L, 10L));
        plannedExerciseDataList.add(new PlannedExerciseData(exerciseId2, 2L, 5L));
        ExercisePlanDTO planDTO = new ExercisePlanDTO(userNum, plannedExerciseDataList, startTime, endTime, details);
        Long savePlan = exercisePlanService.savePlan(planDTO);


        assertThat(savePlan)
                .as("저장한 계획과 조회한 계획은 같아야 함")
                .isEqualTo(exercisePlanService.findPlanById(savePlan).getId());
    }
}