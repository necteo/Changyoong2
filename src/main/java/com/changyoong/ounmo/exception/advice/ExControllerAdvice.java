package com.changyoong.ounmo.exception.advice;

import com.changyoong.ounmo.dto.user.LoginResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(IllegalArgumentException.class)
    public LoginResponseDTO illegalArgumentExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return LoginResponseDTO.builder()
                .isNewUser(Boolean.TRUE)
                .build();
    }
}
