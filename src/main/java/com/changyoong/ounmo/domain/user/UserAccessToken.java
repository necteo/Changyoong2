package com.changyoong.ounmo.domain.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER_ACCESS_TOKEN")
public class UserAccessToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessTokenSeq;

    private Long userId;
    private String accessToken;
    private LocalDateTime expiresAt;

    public UserAccessToken(Long userId, String accessToken, LocalDateTime expiresAt) {
        this.userId = userId;
        this.accessToken = accessToken;
        this.expiresAt = expiresAt;
    }
}
