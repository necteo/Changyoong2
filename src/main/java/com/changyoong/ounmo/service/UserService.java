package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.user.User;

public interface UserService {
    Long saveUser(User user);
    User findUserById(Long userId);
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    String login(String username, String userPw);
}
