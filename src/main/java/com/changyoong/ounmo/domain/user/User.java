package com.changyoong.ounmo.domain.user;

import com.changyoong.ounmo.domain.exercise.ExercisePlan;
import com.changyoong.ounmo.domain.exercise.ExerciseRecord;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    private String id;
    private String pw;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birth;

    private String name;

    private Integer height;
    private Integer weight;
    private String gender;

    @OneToMany(mappedBy = "user")
    private List<ExercisePlan> plans = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ExerciseRecord> exerciseRecords = new ArrayList<>();

    public User(Long num, String id, String pw, LocalDate birth, String name, Integer height, Integer weight, String gender) {
        this.num = num;
        this.id = id;
        this.pw = pw;
        this.birth = birth;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }
}
