package com.changyoong.ounmo.controller;

import com.changyoong.ounmo.dto.LoginUserDTO;
import com.changyoong.ounmo.domain.user.User;
import com.changyoong.ounmo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/ounmo")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public Long signUp(@RequestBody User user) {
        System.out.println("Sign Up : " + user.getName());
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginUserDTO loginUserDTO) {
        System.out.println("Login : " + loginUserDTO.getId());
        return userService.login(loginUserDTO.getId(), loginUserDTO.getPw());
    }
}
