package com.changyoong.ounmo.persistence;

import com.changyoong.ounmo.domain.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByNum(Long num);
    Optional<User> findById(String id);
    Optional<User> findUserByIdAndPw(String id, String Pw);
}
