package com.changyoong.ounmo.service.exercise;

import com.changyoong.ounmo.domain.exercise.*;
import com.changyoong.ounmo.domain.user.Users;
import com.changyoong.ounmo.dto.exericse.ExerciseDTO;
import com.changyoong.ounmo.dto.exericse.ExercisePlanDTO;
import com.changyoong.ounmo.dto.exericse.PlannedExerciseDTO;
import com.changyoong.ounmo.mapper.ExerciseMapper;
import com.changyoong.ounmo.mapper.ExercisePlanMapper;
import com.changyoong.ounmo.repository.exercise.ExercisePlanRepository;
import com.changyoong.ounmo.repository.exercise.ExerciseRepository;
import com.changyoong.ounmo.repository.exercise.PlannedExerciseRepository;
import com.changyoong.ounmo.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<ExercisePlanDTO> findAll() {
        List<ExercisePlan> exercisePlans = (List<ExercisePlan>) exercisePlanRepository.findAll();
        return ExercisePlanMapper.INSTANCE.toExercisePlanDTOList(exercisePlans);
    }

    @Override
    public ExercisePlanDTO findPlanById(Long planId) {
        ExercisePlan exercisePlan = exercisePlanRepository.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("plan doesn't exist"));
        return ExercisePlanMapper.INSTANCE.toExercisePlanDTO(exercisePlan);
    }

    @Override
    public Long savePlan(ExercisePlanDTO planDTO, String email) {
        Users users = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));

        List<PlannedExercise> plannedExercises = new ArrayList<>();
        for (PlannedExerciseDTO ped : planDTO.getPlannedExerciseDTOList()) {
            Exercise exercise = exerciseRepository.findById(ped.getExerciseId())
                    .orElseThrow(() -> new IllegalArgumentException("exercise doesn't exist"));
            plannedExercises.add(PlannedExercise.create(exercise, ped.getSets(), ped.getCount()));
        }

        ExercisePlan exercisePlan = ExercisePlan.createPlan(users, plannedExercises,
                planDTO.getStartTime(), planDTO.getEndTime(), planDTO.getDetails());
        exercisePlanRepository.save(exercisePlan);
        plannedExerciseRepository.saveAll(plannedExercises);
        return exercisePlan.getId();
    }

    @Override
    public Long addExercise(Long planId, PlannedExerciseDTO ped) {
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
    public List<ExerciseDTO> recommendExercise(Boolean isEquipment, ExercisePartName partName) {
        List<Exercise> recommendedExercises = exerciseRepository.findAllByEquipmentAndPart(isEquipment, partName);
        return ExerciseMapper.INSTANCE.toExerciseDTOList(recommendedExercises);
    }

    @Override
    public Long modifyPlan(ExercisePlanDTO planDTO) {
        List<PlannedExercise> plannedExercises = new ArrayList<>();
        ExercisePlan exercisePlan = exercisePlanRepository.findById(planDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("plan doesn't exist"));
        planDTO.getPlannedExerciseDTOList().forEach(ped -> {
            PlannedExercise plannedExercise = plannedExerciseRepository.findById(ped.getId())
                    .orElseThrow(() -> new IllegalArgumentException("plannedExercise doesn't exist"));
            plannedExercise.setSets(ped.getSets());
            plannedExercise.setCount(ped.getCount());
            Exercise newExercise = exerciseRepository.findById(ped.getExerciseId())
                    .orElseThrow(() -> new IllegalArgumentException("exercise doesn't exist"));
            plannedExercise.setExercise(newExercise);
            plannedExercises.add(plannedExercise);
        });
        exercisePlan.setStartTime(planDTO.getStartTime());
        exercisePlan.setEndTime(planDTO.getEndTime());
        exercisePlan.setDetails(planDTO.getDetails());
        exercisePlan.setPlannedExercises(plannedExercises);
        return exercisePlanRepository.findById(planDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("plan doesn't exist")).getId();
    }

    @Override
    public void removePlan(Long planId) {
        exercisePlanRepository.deleteById(planId);
    }
}
