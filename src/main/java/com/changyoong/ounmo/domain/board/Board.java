package com.changyoong.ounmo.domain.board;

import com.changyoong.ounmo.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_NUM")
    private User user;

    @OneToMany(mappedBy = "board")
    private List<File> files = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<Like> likes = new ArrayList<>();

    public void setUser(User user) {
        this.user = user;
        user.getBoards().add(this);
    }

    public void addFile(File file) {
        this.files.add(file);
        file.setBoard(this);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
        comment.setBoard(this);
    }

    public void addLike(Like like) {
        this.likes.add(like);
        like.setBoard(this);
    }

    public static Board createBoard(String title, String content) {
        Board board = new Board();
        board.title = title;
        board.content = content;
        board.createdAt = LocalDateTime.now();
        return board;
    }
}
