package com.changyoong.ounmo.service.board;

import com.changyoong.ounmo.dto.board.BoardDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> findAll();
    BoardDTO findById(Long boardId);
    Long saveBoard(BoardDTO boardDTO, String email);
    Long modifyBoard(BoardDTO boardDTO);
    void removeBoard(Long boardId);
}
