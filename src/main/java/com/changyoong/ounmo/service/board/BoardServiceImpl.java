package com.changyoong.ounmo.service.board;

import com.changyoong.ounmo.domain.board.Board;
import com.changyoong.ounmo.domain.board.File;
import com.changyoong.ounmo.domain.user.Users;
import com.changyoong.ounmo.dto.board.BoardDTO;
import com.changyoong.ounmo.mapper.BoardMapper;
import com.changyoong.ounmo.repository.board.BoardRepository;
import com.changyoong.ounmo.repository.board.FileRepository;
import com.changyoong.ounmo.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;
    private final FileRepository fileRepository;
    private final UserRepository userRepository;

    @Override
    public List<BoardDTO> findAll() {
        List<Board> boards = boardRepository.findAll();
        return BoardMapper.INSTANCE.toBoardDTOList(boards);
    }

    @Override
    public BoardDTO findById(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new IllegalArgumentException("board doesn't exist"));
        return BoardMapper.INSTANCE.toBoardDTO(board);
    }

    @Override
    public Long saveBoard(BoardDTO boardDTO, String email) {
        Board board = Board.createBoard(boardDTO.getTitle(), boardDTO.getContent());
        boardDTO.getFilePaths().forEach(filePath -> board.addFile(File.createFile(filePath)));
        Users users = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
        board.setUsers(users);
        Long saveId = boardRepository.save(board).getId();
        fileRepository.saveAll(board.getFiles());
        return saveId;
    }

    @Override
    public Long modifyBoard(BoardDTO boardDTO) {
        Board board = boardRepository.findById(boardDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("board doesn't exist"));
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setFiles(new ArrayList<>());
        fileRepository.deleteByBoardId(boardDTO.getId());
        boardDTO.getFilePaths().forEach(filePath -> board.addFile(File.createFile(filePath)));
        fileRepository.saveAll(board.getFiles());
        return board.getId();
    }

    @Override
    public void removeBoard(Long boardId) {
        fileRepository.deleteByBoardId(boardId);
        boardRepository.deleteById(boardId);
    }
}
