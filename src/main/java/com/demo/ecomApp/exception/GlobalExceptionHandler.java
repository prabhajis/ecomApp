package com.demo.ecomApp.exception;

import com.demo.ecomApp.dto.ExceptionDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EcomException.class)
    public ResponseEntity<ExceptionDTO> handleYourCustomException(EcomException e) {
        ExceptionDTO exceptionResponse = new ExceptionDTO();
        exceptionResponse.setMessageId(e.getMessageId());
        exceptionResponse.setText(e.getText());
        exceptionResponse.setVariables(e.getVariables());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
    }

}