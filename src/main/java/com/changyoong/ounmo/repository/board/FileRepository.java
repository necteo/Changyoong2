package com.changyoong.ounmo.repository.board;

import com.changyoong.ounmo.domain.board.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
    void deleteByBoardId(Long boardId);
}
