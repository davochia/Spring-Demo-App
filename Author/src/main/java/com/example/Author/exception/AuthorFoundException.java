package com.example.Author.exception;

public class AuthorFoundException extends RuntimeException{
    public AuthorFoundException() {
        super("Email or Phone is already in use");
    }
}
