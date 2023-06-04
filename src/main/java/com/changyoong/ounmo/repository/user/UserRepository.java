package com.changyoong.ounmo.repository.user;

import com.changyoong.ounmo.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    Optional<User> findUserByUsernameAndPassword(String username, String password);
}
