package com.example.Admin.service.AdminServiceImpl;

import com.example.Admin.dto.AdminDto;
import com.example.Admin.model.Author;
import com.example.Admin.model.Book;
import com.example.Admin.model.Customer;
import com.example.Admin.repository.AdminRepository;
import com.example.Admin.service.AdminServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminServiceI {

    @Autowired
    private AdminRepository adminRepository;

    /**
     * @param newBook
     * @return
     */
    @Override
    public Book addBook(Book newBook) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Book findBookById(Integer id) {
        return null;
    }

    /**
     * @param bookTitle
     * @return
     */
    @Override
    public List<Book> findBookByTitle(String bookTitle) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Book> getBooks() {
        return null;
    }

    /**
     * @param id
     * @param newBook
     * @return
     */
    @Override
    public Book modifyBook(Integer id, Book newBook) {
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void removeBook(Integer id) {

    }

    /**
     * @param authorId
     * @return
     */
    @Override
    public List<Book> findBookByAuthorId(String authorId) {
        return null;
    }

    /**
     * @param customer
     * @return
     */
    @Override
    public Customer addCustomer(Customer customer) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Customer findCustomerById(Integer id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Customer> getCustomers() {
        return null;
    }

    /**
     * @param id
     * @param customer
     * @return
     */
    @Override
    public Customer modifyCustomer(Integer id, Customer customer) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean removeCustomer(Integer id) {
        return null;
    }

    /**
     * @param author
     * @return
     */
    @Override
    public Author addAuthor(Author author) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Author findAuthorById(Integer id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<Author> getAuthors() {
        return null;
    }

    /**
     * @param id
     * @param author
     * @return
     */
    @Override
    public Author modifyAuthor(Integer id, Author author) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean removeAuthor(Integer id) {
        return null;
    }

    /**
     * @param adminDto
     * @return
     */
    @Override
    public AdminDto addAdmin(AdminDto adminDto) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public AdminDto findAdminById(Integer id) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public List<AdminDto> getAdmins() {
        return null;
    }

    /**
     * @param id
     * @param adminDto
     * @return
     */
    @Override
    public AdminDto modifyAdmin(Integer id, AdminDto adminDto) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Boolean removeAdmin(Integer id) {
        return null;
    }
}
