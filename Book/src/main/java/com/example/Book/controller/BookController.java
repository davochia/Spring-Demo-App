package com.example.Book.controller;

import com.example.Book.dto.BookDto;
import com.example.Book.exception.BookNotFoundException;
import com.example.Book.service.BookServiceImpl.BookServiceImpl;
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
@RequestMapping("api/books")
public class BookController {

    @Autowired
    private BookServiceImpl bookService;

    @PostMapping
    @Operation(summary="Add a new Book to system")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto newBook) {
        return new ResponseEntity<>(bookService.addBook(newBook), HttpStatus.CREATED);
    }


    @GetMapping
    @Operation(summary="Get all Books from system")
    public ResponseEntity<List<BookDto>> getAll() {
        return new ResponseEntity<>(bookService.getBooks(), HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @Operation(summary="Get Book from system find by id")
    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@Parameter(description = "id of Book to be searched") @PathVariable Integer id) throws BookNotFoundException {
        return new ResponseEntity<>(bookService.findBookById(id), HttpStatus.OK);
    }


    @PutMapping("/{id}")
    @Operation(summary="Edit book in the system")
    public ResponseEntity<BookDto> editBook(@PathVariable Integer id, @RequestBody BookDto newBook) {
        return new ResponseEntity<>(bookService.modifyBook(id, newBook), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Delete book from system")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) throws BookNotFoundException {
        bookService.removeBook(id);
        return new ResponseEntity<>("Book with ID: " + id + " deleted", HttpStatus.NO_CONTENT);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = BookDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid title supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @Operation(summary="Get Book from system find by title")
    @GetMapping("/byTitle/{bookTitle}")
    public ResponseEntity<List<BookDto>> findByTitle(@PathVariable String bookTitle) {
        return new ResponseEntity<>(bookService.findBookByTitle(bookTitle), HttpStatus.OK);
    }


    @Operation(summary="Get Book from system find by author id")
    @GetMapping("/byAuthor/{authorId}")
    public ResponseEntity<List<BookDto>> findByAuthorId(@PathVariable Integer authorId) {
        return new ResponseEntity<>(bookService.getBookByAuthorId(authorId), HttpStatus.OK);
    }

}
