package com.example.Book.exception;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(Integer id) {
        super("Could not find book with id " + id);
    }

    public BookNotFoundException(String bookTitle) {
        super("Could not find book with title " + bookTitle);
    }
}
