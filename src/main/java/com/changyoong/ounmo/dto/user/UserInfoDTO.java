package com.changyoong.ounmo.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class UserInfoDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private LocalDate birth;
    private String nickname;
    private Integer height;
    private Integer weight;
    private String gender;

    @Builder
    public UserInfoDTO(Long id,
                       String email,
                       String username,
                       String password,
                       LocalDate birth,
                       String nickname,
                       Integer height,
                       Integer weight,
                       String gender) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
        this.birth = birth;
        this.nickname = nickname;
        this.height = height;
        this.weight = weight;
        this.gender = gender;
    }
}
