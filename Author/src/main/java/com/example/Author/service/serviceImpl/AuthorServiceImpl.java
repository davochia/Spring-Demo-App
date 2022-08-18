package com.example.Author.service.serviceImpl;


import com.example.Author.dto.AuthorDto;
import com.example.Author.exception.AuthorNotFoundException;
import com.example.Author.model.Author;
import com.example.Author.model.Book;
import com.example.Author.repository.AuthorRepository;
import com.example.Author.service.AuthorServiceI;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorServiceI {

    private final String BOOKS_BY_AUTHOR_URI = "http://localhost:9100/api/books/byAuthor/";

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();



    @Override
    public AuthorDto addAuthor(AuthorDto authorDto) {

        if (authorDto == null)return null;
        Author author = AuthorDto.getAuthor(authorDto);
        return AuthorDto.getAuthorDto((Author) authorRepository.save(author));

    }

    @Override
    public AuthorDto findAuthorById(Integer id)  throws AuthorNotFoundException {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        return optionalAuthor.map(AuthorDto::getAuthorDto).orElseThrow(() -> new AuthorNotFoundException(id));
    }


    @Override
    public List<AuthorDto> getAuthors() {
        List<Author> authors = (List<Author>) authorRepository.findAll();
        List<AuthorDto> authorDtos = new ArrayList<>();
        authors.forEach(author -> authorDtos.add(AuthorDto.getAuthorDto(author)));
        return authorDtos;
    }

    @Override
    public AuthorDto modifyAuthor(Integer id, AuthorDto authorDto)  {
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        if(optionalAuthor == null)return null;

        Author author = optionalAuthor.get();

        if(authorDto == null) return null;

        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setPhone(authorDto.getPhone());
        author.setEmail(authorDto.getEmail());

        return AuthorDto.getAuthorDto((Author) authorRepository.save(author));

    }

    @Override
    public Boolean removeAuthor(Integer id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor == null)return false;

        Author author = optionalAuthor.get();
        authorRepository.delete(author);
        return true;
    }


    @Override
    public List<Book>  getAuthorBooks(Integer authorId) {
        String uri = BOOKS_BY_AUTHOR_URI + authorId;
        RestTemplate restTemplate = new RestTemplate();
        String data = restTemplate.getForObject(uri, String.class);

        ObjectMapper objectMapper = new ObjectMapper();
        List<Book> bookList = new ArrayList<>();

        try {
            bookList = objectMapper.readValue(data, List.class);
            System.out.println("The author books are:\n " + bookList);

        } catch(Exception e) {
            e.printStackTrace();
        }
        return bookList;
    }

}
