package com.changyoong.ounmo.exception.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice(annotations = RestController.class)
public class ExControllerAdvice {
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return e.getMessage();
    }
}
