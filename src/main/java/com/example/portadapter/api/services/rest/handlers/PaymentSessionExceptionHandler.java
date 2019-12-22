package com.example.portadapter.api.services.rest.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import java.io.IOException;

@ControllerAdvice()
public class PaymentSessionExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> constraintViolationException(HttpServletResponse response, Exception ex) throws IOException {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
