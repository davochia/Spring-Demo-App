package com.example.Book.dto;

import com.example.Book.model.Book;
import lombok.Data;

import java.util.Date;

@Data
public class BookDto {

    private Integer id;
    private String title;
    private Integer authorId;
    private String authorName;
    private Date publication;
    private int numberOfBooks;

    public static Book getBook(BookDto bookDto){
        Book book  = new Book();

        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthorId(bookDto.getAuthorId());
        book.setAuthorName(bookDto.getAuthorName());
        book.setPublication(bookDto.getPublication());
        book.setNumberOfBooks(bookDto.getNumberOfBooks());

        return book;
    }

    public static BookDto getBookDto(Book book){
        BookDto bookDto  = new BookDto();

        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthorId(book.getAuthorId());
        bookDto.setAuthorName(book.getAuthorName());
        bookDto.setPublication(book.getPublication());
        bookDto.setNumberOfBooks(book.getNumberOfBooks());

        return bookDto;
    }
}
