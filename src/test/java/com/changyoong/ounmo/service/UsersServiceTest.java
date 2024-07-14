package com.changyoong.ounmo.service;

import com.changyoong.ounmo.dto.user.UserInfoDTO;
import com.changyoong.ounmo.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class UsersServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void signUp() {
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

        assertThat(userInfoDTO.getUsername())
                .as(() -> "가입 회원과 조회된 회원은 같아야 함")
                .isEqualTo(userService.findUserById(userNum).getUsername());
    }

    @Test
    public void login() {
        String id = "kim12";
        String pw = "qwer12";
        String loginId = userService.login(id, pw);
        assertThat(id)
                .as(() -> "아이디가 같아야함")
                .isEqualTo(userService.findUserByUsername(loginId).getUsername());
    }
}