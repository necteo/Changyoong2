package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void signUp() throws Exception {
        User user = new User();
        user.setName("kim");
        user.setId("kim12");
        user.setPw("qwer12");
        user.setBirth(LocalDate.now());
        user.setHeight(170);
        user.setWeight(60);
        user.setGender("남자");

        Long saveNum = userService.saveUser(user);

        assertThat(user)
                .as(() -> "가입 회원과 조회된 회원은 같아야 함")
                .isEqualTo(userService.findUserByNum(saveNum));
    }

    @Test
    public void login() throws Exception {
        String id = "kim12";
        String pw = "qwer12";
        String loginId = userService.login(id, pw);
        assertThat(id)
                .as(() -> "아이디가 같아야함")
                .isEqualTo(userService.findUserById(loginId).getId());
    }
}