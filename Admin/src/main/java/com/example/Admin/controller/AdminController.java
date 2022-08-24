package com.example.Admin.controller;

import com.example.Admin.dto.AdminDto;
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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("api/admins")
public class AdminController {

    private final String Book_API = "http://localhost:9100/api/books/";
    private final String CUSTOMER_API = "http://localhost:9200/api/customers";
    private final String AUTHOR_API = "http://localhost:9000/api/authors/";
    
    @Autowired
    private AdminServiceImpl adminService;



    //    Admin
    @GetMapping("/getAdmins")
    @Operation(summary="Get all Admins from system")
    public ResponseEntity<List<AdminDto>> getAllAdmins() {
        return new ResponseEntity<>(adminService.getAdmins(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Admin Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AdminDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Admin not found",
                    content = @Content) })
    @Operation(summary="Get Admin from system find by id")
    @GetMapping("/admin/{id}")
    public ResponseEntity<AdminDto> getAdminById(@Parameter(description = "id of Admin to be searched") @PathVariable Integer id) {
        return new ResponseEntity<>(adminService.findAdminById(id), HttpStatus.OK);
    }


    @PostMapping("/admin")
    @Operation(summary="Add a new Admin to system")
    public ResponseEntity<AdminDto> createAdmin(@RequestBody AdminDto adminDto) {
        return new ResponseEntity<>(adminService.addAdmin(adminDto) , HttpStatus.CREATED);
    }


    @PutMapping("/admin/{id}")
    @Operation(summary="Edit Admin in the system")
    public ResponseEntity<AdminDto> editAdmin(@PathVariable Integer id, @RequestBody AdminDto adminDto) {
        return new ResponseEntity<>(adminService.modifyAdmin(id, adminDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/admin/{id}")
    @Operation(summary="Delete Admin from system")
    public ResponseEntity<String> deleteAdmin(@PathVariable Integer id) {
        adminService.removeAdmin(id);
        return new ResponseEntity<>("Admin with ID: " + id + " deleted", HttpStatus.NO_CONTENT);
    }


//    Customers
    @GetMapping("/getCustomers")
    @Operation(summary="Get all Customers from system")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        String uri = CUSTOMER_API ;
        RestTemplate restTemplate = new RestTemplate();
        List<Customer> customers = restTemplate.getForObject(uri, List.class);

        return new ResponseEntity<>(customers, HttpStatus.OK);
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
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@Parameter(description = "id of Customer to be searched") @PathVariable Integer id) {
        String uri = CUSTOMER_API + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        Customer customers = restTemplate.getForObject(uri, Customer.class);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }


    @PostMapping("/customer")
    @Operation(summary="Add a new Customer to system")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer newCustomer) {
        String uri = CUSTOMER_API ;;
        RestTemplate restTemplate = new RestTemplate();
        Customer customer = restTemplate.postForObject(uri, newCustomer, Customer.class);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }


    @PutMapping("/customer/{id}")
    @Operation(summary="Edit Customer in the system")
    public ResponseEntity<Customer> editCustomer(@PathVariable Integer id, @RequestBody Customer newCustomer) {
        String uri = CUSTOMER_API + "/" + id;;
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Customer> request = new HttpEntity<Customer>(newCustomer);
        return restTemplate.exchange(uri, HttpMethod.PUT, request, Customer.class, HttpStatus.CREATED);
      //  return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @DeleteMapping("/customer/{id}")
    @Operation(summary="Delete Customer from system")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id) {
        String uri = CUSTOMER_API + "/" + id;;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri);
        return new ResponseEntity<>("Customer with ID: " + id + " deleted", HttpStatus.NO_CONTENT);
    }


    //    Books
    @PostMapping("/book")
    @Operation(summary="Add a new Book to system")
    public ResponseEntity<Book> createBook(@RequestBody Book newBook) {
        String uri = Book_API ;;
        RestTemplate restTemplate = new RestTemplate();
        Book book = restTemplate.postForObject(uri, newBook, Book.class);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }


    @GetMapping("/books")
    @Operation(summary="Get all Books from system")
    public ResponseEntity<List<Book>> getAllBooks() {
        String uri = Book_API ;
        RestTemplate restTemplate = new RestTemplate();
        List<Book> bookList = restTemplate.getForObject(uri, List.class);

        return new ResponseEntity<>(bookList, HttpStatus.OK);
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
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@Parameter(description = "id of Book to be searched") @PathVariable Integer id) {
        String uri = Book_API + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        Book book = restTemplate.getForObject(uri, Book.class);
        return new ResponseEntity<>(book, HttpStatus.OK);
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
    @GetMapping("/book/{bookTitle}")
    public ResponseEntity<List<Book>> findByTitle(@PathVariable String bookTitle) {
        String uri = Book_API + "/byTitle/" + bookTitle;
        RestTemplate restTemplate = new RestTemplate();
        List<Book> books = restTemplate.getForObject(uri, List.class);
        return new ResponseEntity<>(books, HttpStatus.OK);
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
    @GetMapping("/book/{authorId}")
    public ResponseEntity<List<Book>> findByAuthorId(@PathVariable Integer authorId) {
        String uri = Book_API + "/byAuthor" + authorId;
        RestTemplate restTemplate = new RestTemplate();
        List<Book> books = restTemplate.getForObject(uri, List.class);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }


    @PutMapping("/book/{id}")
    @Operation(summary="Edit book in the system")
    public ResponseEntity<Book> editBook(@PathVariable Integer id, @RequestBody Book newBook) {
        String uri = Book_API + "/" + id;;
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Book> request = new HttpEntity<Book>(newBook);
        return restTemplate.exchange(uri, HttpMethod.PUT, request, Book.class, HttpStatus.CREATED);
    }

    @DeleteMapping("/book/{id}")
    @Operation(summary="Delete book from system")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id)  {
        String uri = Book_API + "/" + id;;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri);
        return new ResponseEntity<>("Book with ID: " + id + " deleted", HttpStatus.NO_CONTENT);
    }


   // Authors
    @GetMapping("/authors")
    @Operation(summary="Get all authors from system")
    public ResponseEntity<List<Author>> getAll() {
        String uri = AUTHOR_API ;
        RestTemplate restTemplate = new RestTemplate();
        List<Author> authorList = restTemplate.getForObject(uri, List.class);

        return new ResponseEntity<>(authorList, HttpStatus.OK);
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
    @GetMapping("/author/{id}")
    public ResponseEntity<Author> getAuthorById(@Parameter(description = "id of author to be searched") @PathVariable Integer id) {
        String uri = AUTHOR_API + "/" + id;
        RestTemplate restTemplate = new RestTemplate();
        Author author = restTemplate.getForObject(uri, Author.class);
        return new ResponseEntity<>(author, HttpStatus.OK);
    }


    @PostMapping("/authors")
    @Operation(summary="Add a new author to system")
    public ResponseEntity<Author> createAuthor(@RequestBody Author newAuthor) {
        String uri = AUTHOR_API ;
        RestTemplate restTemplate = new RestTemplate();
        Author author = restTemplate.postForObject(uri, newAuthor, Author.class);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }


    @PutMapping("/author/{id}")
    @Operation(summary="Edit author in the system")
    public ResponseEntity<Author> editAuthor(@PathVariable Integer id, @RequestBody Author newAuthor) {
        String uri = AUTHOR_API + "/" + id;;
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Author> request = new HttpEntity<Author>(newAuthor);
        return restTemplate.exchange(uri, HttpMethod.PUT, request, Author.class, HttpStatus.CREATED);
    }

    @DeleteMapping("/author/{id}")
    @Operation(summary="Delete author from system")
    public ResponseEntity<String> deleteAuthor(@PathVariable Integer id) {
        String uri = AUTHOR_API + "/" + id;;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri);
        return new ResponseEntity<>("Author with ID: " + id + " deleted", HttpStatus.NO_CONTENT);
    }
}
