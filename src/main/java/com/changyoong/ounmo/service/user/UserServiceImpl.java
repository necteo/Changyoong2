package com.changyoong.ounmo.service.user;

import com.changyoong.ounmo.domain.user.User;
import com.changyoong.ounmo.dto.user.UserInfoDTO;
import com.changyoong.ounmo.mapper.UserMapper;
import com.changyoong.ounmo.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public Long saveUser(UserInfoDTO userInfoDTO, String email) {
        validateDuplicateUser(userInfoDTO);
        User user = User.createUser(userInfoDTO);
        user.setEmail(email);
        return userRepository.save(user).getId();
    }

    @Override
    public UserInfoDTO findUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
        return UserMapper.INSTANCE.toUserInfoDTO(user);
    }

    @Override
    public UserInfoDTO findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
        return UserMapper.INSTANCE.toUserInfoDTO(user);
    }

    @Override
    public UserInfoDTO findUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
        return UserMapper.INSTANCE.toUserInfoDTO(user);
    }

    @Override
    public String login(String username, String userPw) {
        User findUser = userRepository.findUserByUsernameAndPassword(username, userPw)
                .orElseThrow(() -> new IllegalStateException("아이디가 존재하지 않거나 비밀번호가 일치하지 않음"));
        return findUser.getId() + findUser.getUsername();
    }

    private void validateDuplicateUser(UserInfoDTO userInfoDTO) {
        Optional<User> findUser = userRepository.findByUsername(userInfoDTO.getUsername());
        if (findUser.isPresent()) {
            throw new IllegalStateException("중복된 회원 정보");
        }
    }
}
