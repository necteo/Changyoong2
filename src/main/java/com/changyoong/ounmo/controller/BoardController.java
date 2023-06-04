package com.changyoong.ounmo.controller;

import com.changyoong.ounmo.dto.board.BoardDTO;
import com.changyoong.ounmo.dto.board.CommentDTO;
import com.changyoong.ounmo.service.board.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/ounmo/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/all")
    public List<BoardDTO> findAllBoards() {
        return boardService.findAll();
    }

    @GetMapping("/find/{boardId}")
    public BoardDTO findBoardById(@PathVariable("boardId") Long boardId) {
        return boardService.findById(boardId);
    }

    @PostMapping("/upload")
    public Long upload(@RequestBody BoardDTO boardDTO) {
        String email = "";
        return boardService.saveBoard(boardDTO, email);
    }

    @PostMapping("/modify")
    public Long modify(@RequestBody BoardDTO boardDTO) {
        return boardService.modifyBoard(boardDTO);
    }

    @GetMapping("/remove/{boardId}")
    public Long removeBoard(@PathVariable("boardId") Long boardId) {
        boardService.removeBoard(boardId);
        return boardId;
    }

    @GetMapping("/comment/{boardId}")
    public List<CommentDTO> findCommentsByBoardId(@PathVariable Long boardId) {
        return null;
    }
}
