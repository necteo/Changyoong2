package com.changyoong.ounmo.domain.user;

import com.changyoong.ounmo.domain.exercise.ExercisePlan;
import com.changyoong.ounmo.domain.exercise.ExerciseRecord;
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
    private Long id;

    private String email;
    private String username;
    private String pw;

    private LocalDate birth;

    private String nickname;

    private Integer height;
    private Integer weight;
    private String gender;

    @OneToMany(mappedBy = "user")
    private List<ExercisePlan> plans = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ExerciseRecord> exerciseRecords = new ArrayList<>();

    @Builder
    public User(String email, String username, LocalDate birth, String nickname, Integer height, Integer weight, String gender) {
        this.email = email;
        this.username = username;
        this.birth = birth;
        this.nickname = nickname;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }
}
