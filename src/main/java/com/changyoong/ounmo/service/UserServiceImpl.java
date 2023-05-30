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
        return saveUser.getNum();
    }

    @Override
    public User findUserByNum(Long userNum) {
        return userRepository.findByNum(userNum).orElseThrow();
    }

    @Override
    public User findUserById(String userId) {
        return userRepository.findById(userId).orElseThrow();
    }

    @Override
    public String login(String userId, String userPw) {
        Optional<User> findUser = userRepository.findUserByIdAndPw(userId, userPw);
        if (findUser.isPresent()) {
            return findUser.get().getNum() + findUser.get().getId();
        }
        throw new IllegalStateException("아이디가 존재하지 않거나 비밀번호가 일치하지 않음");
    }

    private void validateDuplicateUser(User user) {
        Optional<User> findUser = userRepository.findById(user.getId());
        if (findUser.isPresent()) {
            throw new IllegalStateException("중복된 회원 정보");
        }
    }
}
