package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.exercise.*;
import com.changyoong.ounmo.domain.user.User;
import com.changyoong.ounmo.persistence.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ExercisePlanServiceImpl implements ExercisePlanService {
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExercisePlanRepository exercisePlanRepository;
    private final PlannedExerciseRepository plannedExerciseRepository;

    @Override
    public List<ExercisePlan> findAll() {
        return exercisePlanRepository.findAll();
    }

    @Override
    public ExercisePlan findPlanById(Long planId) {
        return exercisePlanRepository.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("plan doesn't exist"));
    }

    @Override
    public Long savePlan(ExercisePlanDTO planDTO) {
        User user = userRepository.findByNum(planDTO.getUserNum())
                .orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));

        List<PlannedExercise> plannedExercises = new ArrayList<>();
        for (PlannedExerciseData ped : planDTO.getPlannedExerciseDataList()) {
            Exercise exercise = exerciseRepository.findById(ped.getExerciseId())
                    .orElseThrow(() -> new IllegalArgumentException("exercise doesn't exist"));
            plannedExercises.add(PlannedExercise.create(exercise, ped.getSets(), ped.getCount()));
        }

        ExercisePlan exercisePlan = ExercisePlan.createPlan(user, plannedExercises,
                planDTO.getStartTime(), planDTO.getEndTime(), planDTO.getDetails());
        exercisePlanRepository.save(exercisePlan);
        plannedExercises.forEach(plannedExerciseRepository::save);
        return exercisePlan.getId();
    }

    @Override
    public Long addExercise(Long planId, PlannedExerciseData ped) {
        ExercisePlan findPlan = exercisePlanRepository.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("plan doesn't exist"));
        Exercise exercise = exerciseRepository.findById(ped.getExerciseId())
                .orElseThrow(() -> new IllegalArgumentException("exercise doesn't exist"));
        PlannedExercise plannedExercise = PlannedExercise.create(exercise, ped.getSets(), ped.getCount());
        findPlan.addPlannedExercise(plannedExercise);
        PlannedExercise save = plannedExerciseRepository.save(plannedExercise);
        return save.getId();
    }

    @Override
    public List<Exercise> recommendExercise(Boolean equipment, ExercisePartName partName) {
        return exerciseRepository.findAllByEquipmentAndPart(equipment, partName);
    }
}
