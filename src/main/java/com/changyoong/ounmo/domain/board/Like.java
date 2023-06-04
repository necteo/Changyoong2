package com.changyoong.ounmo.domain.board;

import com.changyoong.ounmo.domain.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "LIKES")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOARD_ID")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public void setBoard(Board board) {
        this.board = board;
        board.getLikes().add(this);
    }

    public void setUser(User user) {
        this.user = user;
        user.getLikes().add(this);
    }
}
