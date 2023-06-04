package com.changyoong.ounmo.repository.board;

import com.changyoong.ounmo.domain.board.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByUserId(Long userId);
}
