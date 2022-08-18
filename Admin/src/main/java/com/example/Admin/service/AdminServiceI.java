package com.example.Admin.service;

import com.example.Admin.dto.AdminDto;
import com.example.Admin.model.Author;
import com.example.Admin.model.Book;
import com.example.Admin.model.Customer;

import java.util.List;

public interface AdminServiceI {
    Book addBook(Book newBook);
    Book findBookById(Integer id);
    List<Book> findBookByTitle(String bookTitle);
    List<Book> getBooks();
    Book modifyBook(Integer id, Book newBook);
    void removeBook(Integer id);
    List<Book> findBookByAuthorId(String authorId);


    Customer addCustomer(Customer customer);
    Customer findCustomerById(Integer id);
    List<Customer> getCustomers( );
    Customer modifyCustomer(Integer id, Customer customer);
    Boolean removeCustomer(Integer id) ;


    Author addAuthor(Author author);
    Author findAuthorById(Integer id);
    List<Author> getAuthors( );
    Author modifyAuthor(Integer id, Author author);
    Boolean removeAuthor(Integer id) ;


    AdminDto addAdmin(AdminDto adminDto);
    AdminDto findAdminById(Integer id) ;
    List<AdminDto> getAdmins( );
    AdminDto modifyAdmin(Integer id, AdminDto adminDto);
    Boolean removeAdmin(Integer id) ;
}
