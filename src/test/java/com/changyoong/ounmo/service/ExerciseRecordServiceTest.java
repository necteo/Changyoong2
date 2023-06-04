package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.ExercisePartName;
import com.changyoong.ounmo.dto.exericse.ConductExerciseRecordDTO;
import com.changyoong.ounmo.dto.exericse.ExerciseDTO;
import com.changyoong.ounmo.dto.exericse.ExerciseRecordDTO;
import com.changyoong.ounmo.dto.user.UserInfoDTO;
import com.changyoong.ounmo.service.exercise.ExerciseRecordService;
import com.changyoong.ounmo.service.exercise.ExerciseService;
import com.changyoong.ounmo.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ExerciseRecordServiceTest {
    @Autowired
    ExerciseRecordService exerciseRecordService;
    @Autowired
    UserService userService;
    @Autowired
    ExerciseService exerciseService;

    @Test
    void saveRecord() {
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

        List<ConductExerciseRecordDTO> conductExerciseRecordDTOList = new ArrayList<>();
        conductExerciseRecordDTOList.add(ConductExerciseRecordDTO.builder()
                .sets(5L)
                .count(10L)
                .isExercised(true)
                .exerciseId(exerciseId)
                .build());
        conductExerciseRecordDTOList.add(ConductExerciseRecordDTO.builder()
                .sets(10L)
                .count(20L)
                .isExercised(true)
                .exerciseId(exerciseId2)
                .build());
        ExerciseRecordDTO exerciseRecordDTO = ExerciseRecordDTO.builder()
                .date(LocalDate.of(2023, 5, 30))
                .time(90L)
                .calories(1800L)
                .userId(userNum)
                .conductExerciseRecordDTOList(conductExerciseRecordDTOList)
                .build();

        Long savedRecordId = exerciseRecordService.saveRecord(exerciseRecordDTO, "email");

        assertThat(savedRecordId)
                .as("저장한 기록과 조회한 기록이 같아야 함")
                .isEqualTo(exerciseRecordService.findById(savedRecordId).getId());
    }
}