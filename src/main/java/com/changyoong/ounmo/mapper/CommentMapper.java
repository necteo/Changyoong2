package com.changyoong.ounmo.mapper;

import com.changyoong.ounmo.domain.board.Comment;
import com.changyoong.ounmo.dto.board.CommentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    default CommentDTO toCommentDTO(Comment comment) {
        return CommentDTO.builder()
                .id(comment.getId())
                .boardId(comment.getBoard().getId())
                .content(comment.getContent())
                .createdAt(comment.getCreatedAt())
                .build();
    }

    default List<CommentDTO> toCommentDTOList(List<Comment> comments) {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        comments.forEach(comment ->
                commentDTOList.add(CommentMapper.INSTANCE.toCommentDTO(comment)));
        return commentDTOList;
    }
}
