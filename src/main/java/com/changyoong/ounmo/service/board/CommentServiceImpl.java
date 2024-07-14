package com.changyoong.ounmo.service.board;

import com.changyoong.ounmo.domain.board.Comment;
import com.changyoong.ounmo.domain.user.Users;
import com.changyoong.ounmo.dto.board.CommentDTO;
import com.changyoong.ounmo.mapper.CommentMapper;
import com.changyoong.ounmo.repository.board.CommentRepository;
import com.changyoong.ounmo.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    @Override
    public List<CommentDTO> findAllByBoardId(Long boardId) {
        List<Comment> comments = commentRepository.findAllByBoardId(boardId);
        return CommentMapper.INSTANCE.toCommentDTOList(comments);
    }

    @Override
    public Long saveComment(CommentDTO commentDTO, String email) {
        Users users = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("user doesn't exist"));
        Comment comment = Comment.createComment(commentDTO.getContent());
        comment.setUsers(users);
        return commentRepository.save(comment).getId();
    }

    @Override
    public Long modifyComment(CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentDTO.getId())
                .orElseThrow(() -> new IllegalArgumentException("comment doesn't exist"));
        comment.setContent(commentDTO.getContent());
        return comment.getId();
    }

    @Override
    public void removeComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }
}
