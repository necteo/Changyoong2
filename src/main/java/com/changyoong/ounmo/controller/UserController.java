package com.changyoong.ounmo.controller;

import com.changyoong.ounmo.dto.user.LoginUserDTO;
import com.changyoong.ounmo.dto.user.SocialOAuthInfo;
import com.changyoong.ounmo.dto.user.UserInfoDTO;
import com.changyoong.ounmo.service.user.OauthService;
import com.changyoong.ounmo.service.user.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/ounmo")
public class UserController {
    private final UserService userService;
    private final OauthService oauthService;

    @PostMapping("/signup")
    public @ResponseBody Long signUp(@RequestBody UserInfoDTO userInfoDTO) {
        System.out.println("Sign Up : " + userInfoDTO.getNickname());
        return userService.saveUser(userInfoDTO, "email");
    }

    @PostMapping("/login")
    public @ResponseBody String login(@RequestBody LoginUserDTO loginUserDTO, HttpServletRequest request) {
        System.out.println("Login : " + loginUserDTO.getUsername());
        HttpSession session = request.getSession();
        String loginUser = userService.login(loginUserDTO.getUsername(), loginUserDTO.getPw());
        session.setAttribute(loginUser, loginUserDTO);
        return loginUser;
    }

    @PostMapping("/oauth/login")
    public @ResponseBody Long login(HttpServletRequest request) {
        String accessToken = request.getHeader("authorization").substring("Bearer ".length());
        SocialOAuthInfo info = oauthService.oAuthVerify(accessToken);
        return userService.findUserByEmail(info.getEmail()).getId();
    }

    @GetMapping("/user/info")
    public UserInfoDTO getMyPage() {
        return userService.findUserByEmail("email");
    }

    @GetMapping("/delete")
    public @ResponseBody Long deleteAccount() {
        // TODO 회원삭제
        return null;
    }
}
