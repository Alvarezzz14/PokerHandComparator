package com.poker.pokerhandcomparator.exceptions.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Manejo especifico de InavlidCardValueException
    @ExceptionHandler(InvalidCardValueException.class)
    public ResponseEntity<Object> handleInvalidCardValueException(InvalidCardValueException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Invalid Card Value");
        body.put("message", ex.getMessage());
        body.put("path", request.getDescription(false).substring(4)); //ELiminar Uri del path

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    //Manejo de cualquier otra excpecion no controlada (general)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGeneralException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("error", "Internal Server Error");
        body.put("message", "Ocurrió un error en el servidor");
        body.put("path", request.getDescription(false).substring(4)); //ELiminar Uri del path

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
