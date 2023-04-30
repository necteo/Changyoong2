package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.*;
import com.changyoong.ounmo.domain.user.User;
import com.changyoong.ounmo.persistence.ExercisePartRepository;
import com.changyoong.ounmo.persistence.ExercisePlanRepository;
import com.changyoong.ounmo.persistence.ExerciseRepository;
import com.changyoong.ounmo.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class ExercisePlanServiceImpl implements ExercisePlanService {
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExercisePartRepository exercisePartRepository;
    private final ExercisePlanRepository exercisePlanRepository;

    @Override
    public ExercisePlan findPlanById(Long planId) {
        return exercisePlanRepository.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("plan doesn't exist"));
    }

    @Override
    public Long savePlan(Long userNum, List<PlannedExerciseData> plannedExerciseDataList,
                         LocalDateTime startTime, LocalDateTime endTime, String details) {
        User user = userRepository.findByNum(userNum)
                .orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));

        List<PlannedExercise> plannedExercises = new ArrayList<>();
        for (PlannedExerciseData ped : plannedExerciseDataList) {
            Exercise exercise = exerciseRepository.findById(ped.getExerciseId())
                    .orElseThrow(() -> new IllegalArgumentException("exercise doesn't exist"));
            plannedExercises.add(PlannedExercise.create(exercise, ped.getSets(), ped.getCount()));
        }

        ExercisePlan exercisePlan = ExercisePlan.createPlan(user, plannedExercises, startTime, endTime, details);
        exercisePlanRepository.save(exercisePlan);
        return exercisePlan.getId();
    }

    @Override
    public List<Exercise> recommendExercise(Boolean equipment, ExercisePartName partName) {
        return exerciseRepository.findAllByEquipmentAndPart(equipment, partName);
    }
}
