package com.example.Author.exception;

/**
 * @ id requested author id
 */
public class AuthorNotFoundException extends RuntimeException{
    public AuthorNotFoundException(Integer id) {
        super("Could not find author " + id);
    }
}
