package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.user.User;

public interface UserService {
    Long saveUser(User user);
    User findUserByNum(Long userNum);
    User findUserById(String userId);
    String login(String userId, String userPw);
}
