package com.changyoong.ounmo.controller;

import com.changyoong.ounmo.domain.user.LoginUser;
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
    public String login(@RequestBody LoginUser loginUser) {
        System.out.println("Login : " + loginUser.getId());
        return userService.login(loginUser.getId(), loginUser.getPw());
    }
}
