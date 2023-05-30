package com.example.StudentList.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class StudentAlreadyExistsException extends RuntimeException {
    private final HttpStatus status = HttpStatus.CONFLICT;


    public StudentAlreadyExistsException(String message) {
        super(message);
    }
}
