package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import com.changyoong.ounmo.dto.exericse.ExerciseDTO;
import com.changyoong.ounmo.dto.exericse.ExercisePlanDTO;
import com.changyoong.ounmo.dto.exericse.PlannedExerciseDTO;
import com.changyoong.ounmo.dto.user.UserInfoDTO;
import com.changyoong.ounmo.service.exercise.ExercisePlanService;
import com.changyoong.ounmo.service.exercise.ExerciseService;
import com.changyoong.ounmo.service.user.UserService;
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
        ExerciseDTO exerciseDTO = ExerciseDTO.builder()
                .name("푸쉬업")
                .isEquipment(false)
                .img("http://localhost:80/images/push-up.jpg")
                .info("대충 방법과 주의사항")
                .parts(new ArrayList<>(List.of(ExercisePartName.ARM, ExercisePartName.CHEST)))
                .build();

        ExerciseDTO exerciseDTO2 = ExerciseDTO.builder()
                .name("턱걸이")
                .isEquipment(true)
                .img("http://localhost:80/images/pull-up.jpg")
                .info("대충 방법과 주의사항")
                .parts(new ArrayList<>(List.of(ExercisePartName.BACK, ExercisePartName.ARM,
                        ExercisePartName.CHEST, ExercisePartName.SHOULDER)))
                .build();

        exerciseService.saveExercise(exerciseDTO);
        Long exerciseId2 = exerciseService.saveExercise(exerciseDTO2);

        List<ExerciseDTO> recommendedExercises =
                exercisePlanService.recommendExercise(true, ExercisePartName.ARM);

        recommendedExercises.forEach(exercise -> assertThat(exercise.getId())
                .as("추천 운동은 턱걸이여야 함")
                .isEqualTo(exerciseId2));
    }

    @Test
    void savePlan() {
        UserInfoDTO userInfoDTO = UserInfoDTO.builder()
                .username("kim12")
                .password("qwer12")
                .nickname("kim")
                .birth(LocalDate.now())
                .height(170)
                .weight(60)
                .gender("남자")
                .build();
        Long userNum = userService.saveUser(userInfoDTO, "email");

        ExerciseDTO exerciseDTO = ExerciseDTO.builder()
                .name("푸쉬업")
                .isEquipment(false)
                .img("http://localhost:80/images/push-up.jpg")
                .info("대충 방법과 주의사항")
                .parts(new ArrayList<>(List.of(ExercisePartName.ARM, ExercisePartName.CHEST)))
                .build();

        ExerciseDTO exerciseDTO2 = ExerciseDTO.builder()
                .name("턱걸이")
                .isEquipment(true)
                .img("http://localhost:80/images/pull-up.jpg")
                .info("대충 방법과 주의사항")
                .parts(new ArrayList<>(List.of(ExercisePartName.BACK, ExercisePartName.ARM,
                        ExercisePartName.CHEST, ExercisePartName.SHOULDER)))
                .build();

        Long exerciseId = exerciseService.saveExercise(exerciseDTO);
        Long exerciseId2 = exerciseService.saveExercise(exerciseDTO2);

        LocalDateTime startTime = LocalDateTime.of(2023, 4, 30, 20, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 4, 30, 21, 30);
        String details = "어떤 계획인지 설명";
        List<PlannedExerciseDTO> plannedExerciseDTOList = new ArrayList<>();
        plannedExerciseDTOList.add(new PlannedExerciseDTO(1L, 3L, 10L, exerciseId));
        plannedExerciseDTOList.add(new PlannedExerciseDTO(2L, 2L, 5L, exerciseId2));
        ExercisePlanDTO planDTO = new ExercisePlanDTO(userNum, plannedExerciseDTOList, startTime, endTime, details);
        Long savedPlanId = exercisePlanService.savePlan(planDTO, "");


        assertThat(savedPlanId)
                .as("저장한 계획과 조회한 계획은 같아야 함")
                .isEqualTo(exercisePlanService.findPlanById(savedPlanId).getId());
    }

    @Test
    void modifyPlan() {
        UserInfoDTO userInfoDTO = UserInfoDTO.builder()
                .username("kim12")
                .password("qwer12")
                .nickname("kim")
                .birth(LocalDate.now())
                .height(170)
                .weight(60)
                .gender("남자")
                .build();
        Long userNum = userService.saveUser(userInfoDTO, "email");

        ExerciseDTO exerciseDTO = ExerciseDTO.builder()
                .name("푸쉬업")
                .isEquipment(false)
                .img("http://localhost:80/images/push-up.jpg")
                .info("대충 방법과 주의사항")
                .parts(new ArrayList<>(List.of(ExercisePartName.ARM, ExercisePartName.CHEST)))
                .build();

        ExerciseDTO exerciseDTO2 = ExerciseDTO.builder()
                .name("턱걸이")
                .isEquipment(true)
                .img("http://localhost:80/images/pull-up.jpg")
                .info("대충 방법과 주의사항")
                .parts(new ArrayList<>(List.of(ExercisePartName.BACK, ExercisePartName.ARM,
                        ExercisePartName.CHEST, ExercisePartName.SHOULDER)))
                .build();

        Long exerciseId = exerciseService.saveExercise(exerciseDTO);
        Long exerciseId2 = exerciseService.saveExercise(exerciseDTO2);

        LocalDateTime startTime = LocalDateTime.of(2023, 4, 30, 20, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 4, 30, 21, 30);
        String details = "어떤 계획인지 설명";
        List<PlannedExerciseDTO> plannedExerciseDTOList = new ArrayList<>();
        plannedExerciseDTOList.add(new PlannedExerciseDTO(1L, 3L, 10L, exerciseId));
        plannedExerciseDTOList.add(new PlannedExerciseDTO(2L, 2L, 5L, exerciseId2));
        ExercisePlanDTO planDTO = new ExercisePlanDTO(userNum, plannedExerciseDTOList, startTime, endTime, details);
        Long savedPlanId = exercisePlanService.savePlan(planDTO, "");

        /*==============modify================*/

        startTime = LocalDateTime.of(2023, 4, 30, 20, 30);
        endTime = LocalDateTime.of(2023, 4, 30, 22, 30);
        plannedExerciseDTOList.remove(1);
        plannedExerciseDTOList.add(new PlannedExerciseDTO(2L, 5L, 5L, exerciseId2));
        planDTO = ExercisePlanDTO.builder()
                .id(savedPlanId)
                .startTime(startTime)
                .endTime(endTime)
                .userId(userNum)
                .details(details)
                .plannedExerciseDTOList(plannedExerciseDTOList)
                .build();
        Long modifiedPlanId = exercisePlanService.modifyPlan(planDTO);
        ExercisePlanDTO modifiedPlanDTO = exercisePlanService.findPlanById(modifiedPlanId);

        assertThat(modifiedPlanDTO.getEndTime())
                .as("수정할 계획과 수정된 계획의 내용이 같아야 함")
                .isEqualTo(planDTO.getEndTime());
        assertThat(modifiedPlanDTO.getStartTime())
                .as("수정할 계획과 수정된 계획의 내용이 같아야 함")
                .isEqualTo(planDTO.getStartTime());
        assertThat(modifiedPlanDTO.getPlannedExerciseDTOList().get(1).getSets())
                .as("수정할 계획과 수정된 계획의 내용이 같아야 함")
                .isEqualTo(planDTO.getPlannedExerciseDTOList().get(1).getSets());
    }

    @Test
    void removePlan() {
        UserInfoDTO userInfoDTO = UserInfoDTO.builder()
                .username("kim12")
                .password("qwer12")
                .nickname("kim")
                .birth(LocalDate.now())
                .height(170)
                .weight(60)
                .gender("남자")
                .build();
        Long userNum = userService.saveUser(userInfoDTO, "email");

        ExerciseDTO exerciseDTO = ExerciseDTO.builder()
                .name("푸쉬업")
                .isEquipment(false)
                .img("http://localhost:80/images/push-up.jpg")
                .info("대충 방법과 주의사항")
                .parts(new ArrayList<>(List.of(ExercisePartName.ARM, ExercisePartName.CHEST)))
                .build();

        ExerciseDTO exerciseDTO2 = ExerciseDTO.builder()
                .name("턱걸이")
                .isEquipment(true)
                .img("http://localhost:80/images/pull-up.jpg")
                .info("대충 방법과 주의사항")
                .parts(new ArrayList<>(List.of(ExercisePartName.BACK, ExercisePartName.ARM,
                        ExercisePartName.CHEST, ExercisePartName.SHOULDER)))
                .build();

        Long exerciseId = exerciseService.saveExercise(exerciseDTO);
        Long exerciseId2 = exerciseService.saveExercise(exerciseDTO2);

        LocalDateTime startTime = LocalDateTime.of(2023, 4, 30, 20, 0);
        LocalDateTime endTime = LocalDateTime.of(2023, 4, 30, 21, 30);
        String details = "어떤 계획인지 설명";
        List<PlannedExerciseDTO> plannedExerciseDTOList = new ArrayList<>();
        plannedExerciseDTOList.add(new PlannedExerciseDTO(1L, 3L, 10L, exerciseId));
        plannedExerciseDTOList.add(new PlannedExerciseDTO(2L, 2L, 5L, exerciseId2));
        ExercisePlanDTO planDTO = new ExercisePlanDTO(userNum, plannedExerciseDTOList, startTime, endTime, details);
        Long savedPlanId = exercisePlanService.savePlan(planDTO, "");

        exercisePlanService.removePlan(savedPlanId);

        assertThat(savedPlanId)
                .as("계획 삭제 성공 시 1L 반환")
                .isEqualTo(1L);
    }
}