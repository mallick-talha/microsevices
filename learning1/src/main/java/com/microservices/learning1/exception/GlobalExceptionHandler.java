package com.microservices.learning1.exception;

import com.microservices.learning1.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerAlreadyExistException.class)
    public ResponseEntity<ErrorResponseDto>handleCustomerAlreadyException(CustomerAlreadyExistException exception, WebRequest request){
        ErrorResponseDto dto =new ErrorResponseDto(
                request.getDescription(false),
                HttpStatus.BAD_REQUEST,
                exception.getMessage(), LocalDateTime.now()
        );
        return new ResponseEntity<>(dto,HttpStatus.BAD_REQUEST);
    }
}
