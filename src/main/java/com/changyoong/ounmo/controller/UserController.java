package com.changyoong.ounmo.controller;

import com.changyoong.ounmo.dto.LoginUserDTO;
import com.changyoong.ounmo.domain.user.User;
import com.changyoong.ounmo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/ounmo")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public @ResponseBody Long signUp(@RequestBody User user) {
        System.out.println("Sign Up : " + user.getName());
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public @ResponseBody String login(@RequestBody LoginUserDTO loginUserDTO, HttpServletRequest request) {
        System.out.println("Login : " + loginUserDTO.getId());
        HttpSession session = request.getSession();
        String loginUser = userService.login(loginUserDTO.getId(), loginUserDTO.getPw());
        session.setAttribute(loginUser, loginUserDTO);
        return loginUser;
    }

    @GetMapping("/logout")
    public @ResponseBody String logout(HttpServletRequest request) {
        // ToDo : 로그아웃
        return "";
    }

    @GetMapping("/user/{userNum}")
    public User getMyPage(@PathVariable(value = "userNum") Long userNum) {
        // ToDo : 마이페이지 조회
        return new User();
    }
}
