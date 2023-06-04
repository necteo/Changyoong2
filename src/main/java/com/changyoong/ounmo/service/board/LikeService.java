package com.changyoong.ounmo.service.board;

import com.changyoong.ounmo.dto.board.BoardDTO;
import com.changyoong.ounmo.dto.board.LikeDTO;

import java.util.List;

public interface LikeService {
    List<BoardDTO> findBoardsByUser(String email);
    Long saveLike(LikeDTO likeDTO, String email);
    void deleteLike(Long likeId);
}
