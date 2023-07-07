package com.example.StudentList.exception;

import com.example.StudentList.dto.response.ErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(AgeLimitException.class)
    ResponseEntity<ErrorMessage> handleAgeLimitException(AgeLimitException ex) {
        log.info(ex.getMessage(), ex);
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDate(LocalDateTime.now());
        errorMessage.setStatus(ex.getStatus());
        errorMessage.setErrorCode(ex.getStatus().value());
        errorMessage.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorMessage);
    }

    @ExceptionHandler(NotFoundException.class)
    ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException ex) {
        log.info(ex.getMessage(), ex);
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDate(LocalDateTime.now());
        errorMessage.setStatus(ex.getStatus());
        errorMessage.setErrorCode(ex.getStatus().value());
        errorMessage.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorMessage);
    }

    @ExceptionHandler(StudentAlreadyExistsException.class)
    ResponseEntity<ErrorMessage> handleStudentAlreadyExistsException(StudentAlreadyExistsException ex) {
        log.info(ex.getMessage(), ex);
        ErrorMessage errorMessage = new ErrorMessage();
        errorMessage.setDate(LocalDateTime.now());
        errorMessage.setStatus(ex.getStatus());
        errorMessage.setErrorCode(ex.getStatus().value());
        errorMessage.setErrorMessage(ex.getMessage());
        return ResponseEntity.status(ex.getStatus()).body(errorMessage);
    }

//
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    ResponseEntity<ErrorMessage> handleConstraintViolationException(MethodArgumentNotValidException ex) {
//        log.info(ex.getMessage(), ex);
//        ErrorMessage errorMessage = new ErrorMessage();
//        errorMessage.setDate(LocalDateTime.now());
//        errorMessage.setStatus(HttpStatus.BAD_REQUEST);
//        errorMessage.setErrorCode(HttpStatus.BAD_REQUEST.value());
//        List<FieldError> list = ex.getFieldErrors();
//        String stringBuilder = list.stream().map(
//                fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage() + "; ").collect(Collectors.joining());
//        errorMessage.setErrorMessage(stringBuilder);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
//    }
}
