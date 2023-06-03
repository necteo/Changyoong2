package com.changyoong.ounmo.controller;

import com.changyoong.ounmo.dto.SocialOAuthInfo;
import com.changyoong.ounmo.service.OauthService;
import com.changyoong.ounmo.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth-test")
public class LoginController {
    private final UserService userService;
    private final OauthService oauthService;

    @PostMapping("/login")
    public @ResponseBody Long login(HttpServletRequest request) {
        String accessToken = request.getHeader("authorization").substring("Bearer ".length());
        log.info("Received Access Token : {}", accessToken);
        SocialOAuthInfo info = oauthService.oAuthLogin(accessToken);
        return userService.findUserByEmail(info.getEmail()).getId();
    }
}