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
public class User {
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

    @OneToMany(mappedBy = "user")
    private List<ExercisePlan> plans = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<ExerciseRecord> exerciseRecords = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Like> likes = new ArrayList<>();

    public static User createUser(UserInfoDTO userInfoDTO) {
        User user = new User();
        user.setUsername(userInfoDTO.getUsername());
        user.setPassword(userInfoDTO.getPassword());
        user.setNickname(user.getNickname());
        user.setBirth(userInfoDTO.getBirth());
        user.setHeight(userInfoDTO.getHeight());
        user.setWeight(userInfoDTO.getWeight());
        user.setGender(userInfoDTO.getGender());
        return user;
    }
}
