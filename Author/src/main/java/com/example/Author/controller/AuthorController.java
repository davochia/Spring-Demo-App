package com.example.Author.controller;


import com.example.Author.dto.AuthorDto;
import com.example.Author.exception.AuthorNotFoundException;
import com.example.Author.model.Book;
import com.example.Author.service.serviceImpl.AuthorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/authors")
public class AuthorController {

    @Autowired
    private AuthorServiceImpl authorServiceImpl;

    @GetMapping
    @Operation(summary="Get all authors from system")
    public ResponseEntity<List<AuthorDto>> getAll() {
        return new ResponseEntity<>(authorServiceImpl.getAuthors(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Author not found",
                    content = @Content) })
    @Operation(summary="Get author from system find by id")
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@Parameter(description = "id of author to be searched") @PathVariable Integer id) throws AuthorNotFoundException {
        return new ResponseEntity<>(authorServiceImpl.findAuthorById(id), HttpStatus.OK);
    }


    @Operation(summary="Get books find by author id")
    @GetMapping("/books/{authorId}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@Parameter(description = "id of author") @PathVariable Integer authorId) {
        return new ResponseEntity<>(authorServiceImpl.getAuthorBooks(authorId), HttpStatus.OK);
    }


    @PostMapping
    @Operation(summary="Add a new author to system")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorServiceImpl.addAuthor(authorDto) , HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Operation(summary="Edit author in the system")
    public ResponseEntity<AuthorDto> editAuthor(@PathVariable Integer id, @RequestBody AuthorDto newAuthor) {
        return new ResponseEntity<>(authorServiceImpl.modifyAuthor(id, newAuthor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Delete author from system")
    public ResponseEntity<String> deleteAuthor(@PathVariable Integer id) {
        authorServiceImpl.removeAuthor(id);
        return new ResponseEntity<>("Author with ID: " + id + " deleted", HttpStatus.NO_CONTENT);
    }

}