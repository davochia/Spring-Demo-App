package com.example.Admin.controller;

import com.example.Admin.model.Author;
import com.example.Admin.model.Book;
import com.example.Admin.model.Customer;
import com.example.Admin.service.AdminServiceImpl.AdminServiceImpl;
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
@RequestMapping("api/admins")
public class AdminController {
    
    @Autowired
    private AdminServiceImpl adminService;


//    Customers
    @GetMapping
    @Operation(summary="Get all Customers from system")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return new ResponseEntity<>(adminService.getCustomers(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Customer Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Customer.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Customer not found",
                    content = @Content) })
    @Operation(summary="Get Customer from system find by id")
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@Parameter(description = "id of Customer to be searched") @PathVariable Integer id) {
        return new ResponseEntity<>(adminService.findCustomerById(id), HttpStatus.OK);
    }


    @PostMapping
    @Operation(summary="Add a new Customer to system")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer Customer) {
        return new ResponseEntity<>(adminService.addCustomer(Customer) , HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    @Operation(summary="Edit Customer in the system")
    public ResponseEntity<Customer> editCustomer(@PathVariable Integer id, @RequestBody Customer newCustomer) {
        return new ResponseEntity<>(adminService.modifyCustomer(id, newCustomer), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Delete Customer from system")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        adminService.removeCustomer(id);
        return new ResponseEntity<>("Customer with ID: " + id + " deleted", HttpStatus.NO_CONTENT);
    }


//    Books
@PostMapping
@Operation(summary="Add a new Book to system")
public ResponseEntity<Book> createBook(@RequestBody Book newBook) {
    return new ResponseEntity<>(adminService.addBook(newBook), HttpStatus.CREATED);
}


    @GetMapping
    @Operation(summary="Get all Books from system")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(adminService.getBooks(), HttpStatus.OK);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @Operation(summary="Get Book from system find by id")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@Parameter(description = "id of Book to be searched") @PathVariable Integer id) {
        return new ResponseEntity<>(adminService.findBookById(id), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid title supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @Operation(summary="Get Book from system find by title")
    @GetMapping("/{bookTitle}")
    public List<Book> findByTitle(@PathVariable String bookTitle) {
        return adminService.findBookByTitle(bookTitle);
    }


    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid author id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @Operation(summary="Get Book from system find by author id")
    @GetMapping("/author/{authorId}")
    public List<Book> findByAuthorId(@PathVariable String authorId) {
        return adminService.findBookByAuthorId(authorId);
    }


    @PutMapping("/{id}")
    @Operation(summary="Edit book in the system")
    public ResponseEntity<Book> editBook(@PathVariable Integer id, @RequestBody Book newBook) {
        return new ResponseEntity<>(adminService.modifyBook(id, newBook), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @Operation(summary="Delete book from system")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id)  {
        adminService.removeBook(id);
        return new ResponseEntity<>("Book with ID: " + id + " deleted", HttpStatus.NO_CONTENT);
    }
    
    
//    Admin
@GetMapping // Returns all authors
@Operation(summary="Get all authors from system")
public ResponseEntity<List<Author>> getAll() {
    return new ResponseEntity<>(adminService.getAuthors(), HttpStatus.OK);
}

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Author.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Author not found",
                    content = @Content) })
    @Operation(summary="Get author from system find by id")
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@Parameter(description = "id of author to be searched") @PathVariable Integer id) {
        return new ResponseEntity<>(adminService.findAuthorById(id), HttpStatus.OK);
    }


    @PostMapping // Create  new author
    @Operation(summary="Add a new author to system")
    public ResponseEntity<Author> createAuthor(@RequestBody Author Author) {
        return new ResponseEntity<>(adminService.addAuthor(Author) , HttpStatus.CREATED);
    }


    @PutMapping("/{id}") // Edith author
    @Operation(summary="Edit author in the system")
    public ResponseEntity<Author> editAuthor(@PathVariable Integer id, @RequestBody Author newAuthor) {
        return new ResponseEntity<>(adminService.modifyAuthor(id, newAuthor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}") // Delete author
    @Operation(summary="Delete author from system")
    public ResponseEntity<String> deleteAuthor(@PathVariable Integer id) {
        adminService.removeAuthor(id);
        return new ResponseEntity<>("Author with ID: " + id + " deleted", HttpStatus.NO_CONTENT);
    }
}
