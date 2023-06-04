package com.changyoong.ounmo.service.user;

import com.changyoong.ounmo.dto.user.UserInfoDTO;

public interface UserService {
    Long saveUser(UserInfoDTO userInfoDTO, String email);
    UserInfoDTO findUserById(Long userId);
    UserInfoDTO findUserByEmail(String email);
    UserInfoDTO findUserByUsername(String username);
    String login(String username, String userPw);
}
