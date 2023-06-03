package com.changyoong.ounmo.repository;

import com.changyoong.ounmo.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findUserByUsernameAndPw(String username, String Pw);
}
