package com.changyoong.ounmo.repository.board;

import com.changyoong.ounmo.domain.board.Board;
import com.changyoong.ounmo.domain.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByUsers(Users users);
}
