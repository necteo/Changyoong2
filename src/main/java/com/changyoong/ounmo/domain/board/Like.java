package com.changyoong.ounmo.domain.board;

import com.changyoong.ounmo.domain.user.Users;
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
    private Users users;

    public void setBoard(Board board) {
        this.board = board;
        board.getLikes().add(this);
    }

    public void setUsers(Users users) {
        this.users = users;
        users.getLikes().add(this);
    }
}
