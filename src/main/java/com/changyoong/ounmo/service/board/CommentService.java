package com.changyoong.ounmo.service.board;


import com.changyoong.ounmo.dto.board.CommentDTO;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findAllByBoardId(Long boardId);
    Long saveComment(CommentDTO commentDTO, String email);
    Long modifyComment(CommentDTO commentDTO);
    void removeComment(Long commentId);
}
