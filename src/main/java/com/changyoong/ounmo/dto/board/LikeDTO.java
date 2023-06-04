package com.changyoong.ounmo.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LikeDTO {
    private Long id;
    private Long boardId;

    @Builder
    public LikeDTO(Long id, Long boardId) {
        this.id = id;
        this.boardId = boardId;
    }
}
