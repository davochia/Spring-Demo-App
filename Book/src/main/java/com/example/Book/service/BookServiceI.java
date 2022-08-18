package com.example.Book.service;

import com.example.Book.dto.BookDto;
import com.example.Book.exception.BookNotFoundException;

import java.util.List;

public interface BookServiceI {

    BookDto addBook(BookDto newBook);
    BookDto findBookById(Integer id) throws BookNotFoundException;
    List<BookDto> getBooks();
    BookDto modifyBook(Integer id, BookDto newBook);
    void removeBook(Integer id) throws BookNotFoundException;

    List<BookDto> findBookByTitle(String bookTitle) throws BookNotFoundException;
    List<BookDto> getBookByAuthorId(Integer authorId);
    void borrowBook(Integer bookId, Integer customerId);

}
