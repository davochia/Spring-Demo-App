package com.example.Author.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class AuthorFoundAdvice {
    @ResponseBody
    @ExceptionHandler(AuthorFoundException.class)
    @ResponseStatus(HttpStatus.FOUND)
    String authorFoundHandler(AuthorFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(AuthorNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String authorNotFoundHandler(AuthorNotFoundException ex) {
        return ex.getMessage();
    }
}
