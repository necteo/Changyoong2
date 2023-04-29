package com.changyoong.ounmo.persistence;

import com.changyoong.ounmo.domain.user.User;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User, Long> {
    Optional<User> findByNum(Long num);
    Optional<User> findById(String id);
    Optional<User> findUserByIdAndPw(String id, String Pw);
}
