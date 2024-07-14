package com.changyoong.ounmo.domain.user;

import com.changyoong.ounmo.domain.board.Board;
import com.changyoong.ounmo.domain.board.Comment;
import com.changyoong.ounmo.domain.board.Like;
import com.changyoong.ounmo.domain.exercise.ExercisePlan;
import com.changyoong.ounmo.domain.exercise.ExerciseRecord;
import com.changyoong.ounmo.dto.user.UserInfoDTO;
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
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String username;
    private String password;

    private LocalDate birth;

    private String nickname;

    private Integer height;
    private Integer weight;
    private String gender;

    @OneToMany(mappedBy = "users")
    private List<ExercisePlan> plans = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<ExerciseRecord> exerciseRecords = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "users")
    private List<Like> likes = new ArrayList<>();

    public static Users createUser(UserInfoDTO userInfoDTO) {
        Users users = new Users();
        users.setUsername(userInfoDTO.getUsername());
        users.setPassword(userInfoDTO.getPassword());
        users.setNickname(userInfoDTO.getNickname());
        users.setBirth(userInfoDTO.getBirth());
        users.setHeight(userInfoDTO.getHeight());
        users.setWeight(userInfoDTO.getWeight());
        users.setGender(userInfoDTO.getGender());
        return users;
    }
}
