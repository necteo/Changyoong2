package com.changyoong.ounmo.controller;

import com.changyoong.ounmo.dto.LoginUserDTO;
import com.changyoong.ounmo.domain.user.User;
import com.changyoong.ounmo.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
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

        String content = new ObjectMapper().writeValueAsString(user);

        mockMvc.perform(
                post("/ounmo/signup")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andDo(print());
    }

    @Test
    void login() throws Exception {
        User user = new User(1L, "kim", "kim12", LocalDate.now(), "qwer12", 170, 60, "남자");
        LoginUserDTO loginUserDTO = new LoginUserDTO("kim12", "qwer12");

        given(userService.login(loginUserDTO.getId(), loginUserDTO.getPw())).willReturn(loginUserDTO.getId());
        String content = new ObjectMapper().writeValueAsString(loginUserDTO);

        mockMvc.perform(
                        post("/ounmo/login")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(loginUserDTO.getId()))
                .andDo(print());
    }
}