package com.example.Author.service;

import com.example.Author.dto.AuthorDto;
import com.example.Author.exception.AuthorFoundException;
import com.example.Author.exception.AuthorNotFoundException;
import com.example.Author.model.Book;

import java.util.List;

public interface AuthorServiceI {
    AuthorDto addAuthor(AuthorDto authorDto) throws AuthorFoundException;
    AuthorDto findAuthorById(Integer id) throws AuthorNotFoundException;
    List<AuthorDto> getAuthors( );
    AuthorDto modifyAuthor(Integer id, AuthorDto authorDto)throws AuthorNotFoundException;
    Boolean removeAuthor(Integer id) throws AuthorNotFoundException;

    List<Book> getAuthorBooks(Integer authorId);
}
