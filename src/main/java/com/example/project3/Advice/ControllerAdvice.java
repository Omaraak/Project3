package com.example.project3.Advice;

import com.example.project3.Api.ApiException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiExceptionHandler(ApiException ex){
        return ResponseEntity.status(404).body(ex.getMessage());
    }
    @ExceptionHandler(value = DuplicateKeyException.class)
    public ResponseEntity DuplicateKeyExceptionHandler(DuplicateKeyException ex){
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}
