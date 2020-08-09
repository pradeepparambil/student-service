package com.teksenz.studentservice.web.controllers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class StudentServiceExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<String>> handleConstraintViolation(ConstraintViolationException e){
        List<String> errors = new ArrayList<>(e.getConstraintViolations().size());
        e.getConstraintViolations().forEach(cv->errors.add(cv.getMessage()));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException exc){
        List<String> errors = new ArrayList<>(exc.getBindingResult().getFieldErrors().size());
        exc.getBindingResult().getFieldErrors().forEach(fieldError -> {errors.add("Object : " + fieldError.getObjectName()
                + ", Field : " + fieldError.getField()
                + ", Message : "  + fieldError.getDefaultMessage());});

        ErrorList errorList = ErrorList.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors)
                .time(System.currentTimeMillis())
                .build();
        return new ResponseEntity(errorList, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity handleResourceNotFound(NotFoundException exc){
        ErrorMessage errorMessage = ErrorMessage.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(exc.getMessage())
                .time(System.currentTimeMillis())
                .build();
        return new ResponseEntity(errorMessage,HttpStatus.NOT_FOUND);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static class ErrorMessage{
        private int status;
        private String message;
        private long time;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static class ErrorList{
        private int status;
        private List<String> errors;
        private long time;
    }


}
