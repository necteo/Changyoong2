package com.changyoong.ounmo.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentDTO {
    private Long id;
    private Long boardId;
    private String content;
    private LocalDateTime createdAt;

    @Builder
    public CommentDTO(Long id, Long boardId, String content, LocalDateTime createdAt) {
        this.id = id;
        this.boardId = boardId;
        this.content = content;
        this.createdAt = createdAt;
    }
}
