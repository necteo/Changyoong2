package com.changyoong.ounmo.domain.board;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @Column(name = "FILE_PATH")
    private String filePath;

    public static File createFile(String filePath) {
        File file = new File();
        file.filePath = filePath;
        return file;
    }
}
