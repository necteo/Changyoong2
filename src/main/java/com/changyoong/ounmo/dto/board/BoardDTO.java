package com.changyoong.ounmo.dto.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardDTO {
    private Long id;
    private String title;
    private String content;
    private List<String> filePaths;
    private LocalDateTime createdAt;

    @Builder
    public BoardDTO(Long id, String title, String content, List<String> filePaths, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.filePaths = filePaths;
        this.createdAt = createdAt;
    }
}
