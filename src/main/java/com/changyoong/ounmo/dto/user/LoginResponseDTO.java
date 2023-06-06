package com.changyoong.ounmo.dto.user;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponseDTO {
    private Boolean isNewUser;

    @Builder
    public LoginResponseDTO(Boolean isNewUser) {
        this.isNewUser = isNewUser;
    }
}
