package com.changyoong.ounmo.service.board;

import com.changyoong.ounmo.domain.board.Board;
import com.changyoong.ounmo.domain.board.Like;
import com.changyoong.ounmo.domain.user.User;
import com.changyoong.ounmo.dto.board.BoardDTO;
import com.changyoong.ounmo.dto.board.LikeDTO;
import com.changyoong.ounmo.mapper.BoardMapper;
import com.changyoong.ounmo.repository.board.BoardRepository;
import com.changyoong.ounmo.repository.board.LikeRepository;
import com.changyoong.ounmo.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Override
    public List<BoardDTO> findBoardsByUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
        List<Like> likes = likeRepository.findAllByUserId(user.getId());
        List<Board> boards = new ArrayList<>();
        likes.forEach(like -> boards.add(boardRepository.findById(like.getBoard().getId())
                .orElseThrow(() -> new IllegalArgumentException("board doesn't exist"))));
        return BoardMapper.INSTANCE.toBoardDTOList(boards);
    }

    @Override
    public Long saveLike(LikeDTO likeDTO, String email) {
        Board board = boardRepository.findById(likeDTO.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("board doesn't exist"));
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
        Like like = new Like();
        like.setBoard(board);
        like.setUser(user);
        return likeRepository.save(like).getId();
    }

    @Override
    public void deleteLike(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
