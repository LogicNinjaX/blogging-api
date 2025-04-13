package com.example.blogging_api.exception.exception_handler;

import com.example.blogging_api.dto.ApiResponse;
import com.example.blogging_api.dto.ErrorResponse;
import com.example.blogging_api.exception.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<Map<String, String>>> validationHandler(MethodArgumentNotValidException ex){

        Map<String, String> errors = new LinkedHashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });


        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse<>(false, "invalid request", HttpStatus.BAD_REQUEST.value(), errors));
    }


    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorResponse<?>> postHandler(PostNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse<>(false, ex.getMessage(), HttpStatus.NOT_FOUND.value()));
    }
}
