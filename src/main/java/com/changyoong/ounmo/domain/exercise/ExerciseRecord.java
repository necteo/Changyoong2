package com.changyoong.ounmo.domain.exercise;

import com.changyoong.ounmo.domain.user.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "EXERCISE_RECORD")
public class ExerciseRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private Long time;
    private Long calories;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users users;

    @OneToMany(mappedBy = "record")
    private List<ConductExerciseRecord> conductExerciseRecords = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAN_ID")
    private ExercisePlan plan;

    public void setUsers(Users users) {
        this.users = users;
        users.getExerciseRecords().add(this);
    }

    public void addConductExerciseRecord(ConductExerciseRecord conductExerciseRecord) {
        this.conductExerciseRecords.add(conductExerciseRecord);
        conductExerciseRecord.setRecord(this);
    }

    public static ExerciseRecord createExerciseRecord(LocalDate date, Long time, Long calories, Users users,
                                                      List<ConductExerciseRecord> conductExerciseRecords) {
        ExerciseRecord exerciseRecord = new ExerciseRecord();
        exerciseRecord.setDate(date);
        exerciseRecord.setTime(time);
        exerciseRecord.setCalories(calories);
        exerciseRecord.setUsers(users);
        conductExerciseRecords.forEach(exerciseRecord::addConductExerciseRecord);
        return exerciseRecord;
    }
}
