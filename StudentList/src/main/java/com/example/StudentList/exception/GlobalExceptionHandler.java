package com.example.StudentList.exception;

import com.example.StudentList.dto.response.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AgeLimitException.class)
    ResponseEntity<ErrorMessage> handleException(AgeLimitException ex) {
        log.info(ex.getMessage(), ex);
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDate(LocalDateTime.now());
        errorMessage.setStatus(ex.getStatus());
        errorMessage.setErrorCode(ex.getStatus().value());
        errorMessage.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorMessage);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorMessage> handleException(NotFoundException ex) {
        log.info(ex.getMessage(), ex);
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDate(LocalDateTime.now());
        errorMessage.setStatus(ex.getStatus());
        errorMessage.setErrorCode(ex.getStatus().value());
        errorMessage.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorMessage);
    }

    @ExceptionHandler(StudentAlreadyExistsException.class)
    ResponseEntity<ErrorMessage> handleException(StudentAlreadyExistsException ex) {
        log.info(ex.getMessage(), ex);
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDate(LocalDateTime.now());
        errorMessage.setStatus(ex.getStatus());
        errorMessage.setErrorCode(ex.getStatus().value());
        errorMessage.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorMessage);
    }
}
