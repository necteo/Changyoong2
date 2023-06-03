package com.changyoong.ounmo.service;

import com.changyoong.ounmo.domain.user.User;
import com.changyoong.ounmo.repository.UserRepository;
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
    public Long saveUser(User user) {
        validateDuplicateUser(user);
        User saveUser = userRepository.save(user);
        return saveUser.getId();
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
    }

    @Override
    public String login(String username, String userPw) {
        Optional<User> findUser = userRepository.findUserByUsernameAndPw(username, userPw);
        if (findUser.isPresent()) {
            return findUser.get().getId() + findUser.get().getUsername();
        }
        throw new IllegalStateException("아이디가 존재하지 않거나 비밀번호가 일치하지 않음");
    }

    private void validateDuplicateUser(User user) {
        Optional<User> findUser = userRepository.findByUsername(user.getUsername());
        if (findUser.isPresent()) {
            throw new IllegalStateException("중복된 회원 정보");
        }
    }
}
