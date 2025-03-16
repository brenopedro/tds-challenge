package com.tds.url.exception.config;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.tds.url.exception.UrlNotFoundException;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<String> handleUrlNotFoundException(UrlNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.valueOf(e.getErrorCode()));
    }

}