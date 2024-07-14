package com.changyoong.ounmo.controller;

import com.changyoong.ounmo.dto.user.LoginUserDTO;
import com.changyoong.ounmo.domain.user.Users;
import com.changyoong.ounmo.service.user.OauthService;
import com.changyoong.ounmo.service.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UsersControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OauthService oauthService;
    @MockBean
    UserService userService;

    @Test
    public void signUp() throws Exception {
        Users users = new Users();
        users.setNickname("kim");
        users.setUsername("kim12");
        users.setPassword("qwer12");
        users.setBirth(LocalDate.now());
        users.setHeight(170);
        users.setWeight(60);
        users.setGender("남자");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        String content = objectMapper.writeValueAsString(users);

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
        Users users = new Users();
        users.setNickname("kim");
        users.setUsername("kim12");
        users.setPassword("qwer12");
        users.setBirth(LocalDate.now());
        users.setHeight(170);
        users.setWeight(60);
        users.setGender("남자");
        LoginUserDTO loginUserDTO = new LoginUserDTO("kim12", "qwer12");

        given(userService.login(loginUserDTO.getUsername(), loginUserDTO.getPw())).willReturn(loginUserDTO.getUsername());
        String content = new ObjectMapper().writeValueAsString(loginUserDTO);

        mockMvc.perform(
                        post("/ounmo/login")
                                .content(content)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string(loginUserDTO.getUsername()))
                .andDo(print());
    }
}