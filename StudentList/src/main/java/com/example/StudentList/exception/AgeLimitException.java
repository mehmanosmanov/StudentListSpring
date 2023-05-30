package com.example.StudentList.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class AgeLimitException extends RuntimeException {
    private final HttpStatus status = HttpStatus.BAD_REQUEST;
    public AgeLimitException() {
        super("The age limit is not appropriate");
    }
}
