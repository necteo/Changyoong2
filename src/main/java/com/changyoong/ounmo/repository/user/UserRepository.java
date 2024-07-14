package com.changyoong.ounmo.repository.user;

import com.changyoong.ounmo.domain.user.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Users, Long> {
    Optional<Users> findByEmail(String email);
    Optional<Users> findByUsername(String username);
    Optional<Users> findUserByUsernameAndPassword(String username, String password);
}
