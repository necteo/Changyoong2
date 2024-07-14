package com.changyoong.ounmo.domain.board;

import com.changyoong.ounmo.domain.user.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Users users;

    private String content;
    private LocalDateTime createdAt;

    public void setUsers(Users users) {
        this.users = users;
        users.getComments().add(this);
    }

    public static Comment createComment(String content) {
        Comment comment = new Comment();
        comment.content = content;
        comment.createdAt = LocalDateTime.now();
        return comment;
    }
}
