package com.example.Book.service.BookServiceImpl;

import com.example.Book.dto.BookDto;
import com.example.Book.exception.BookNotFoundException;
import com.example.Book.model.Author;
import com.example.Book.model.Book;
import com.example.Book.repository.BookRepository;
import com.example.Book.service.BookServiceI;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookServiceI {

    final static Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);
    private final String AUTHOR_API = "http://localhost:9000/api/authors/";


    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDto addBook(BookDto newBook) {
        if (newBook == null) return null;

        Integer authorId = newBook.getAuthorId();

        String uri = AUTHOR_API + authorId;
        RestTemplate restTemplate = new RestTemplate();
        String data = restTemplate.getForObject(uri, String.class);

        Gson g = new Gson();
        Author author = g.fromJson(data, Author.class);

        Book book = BookDto.getBook(newBook);
        book.setAuthorName(author.getFirstName() + " " + author.getLastName());
        book.setAuthorId(author.getId());

        return BookDto.getBookDto(bookRepository.save(book));
    }

    @Override
    public BookDto findBookById(Integer id) throws BookNotFoundException{
        Optional<Book> optionalBook = bookRepository.findById(id);
        return optionalBook.map(BookDto::getBookDto).orElseThrow(() -> new BookNotFoundException(id));
    }


    @Override
    public List<BookDto> getBooks() {
        List<Book> bookList = bookRepository.findAll();

        List<BookDto> bookDtoList = new ArrayList<BookDto>();
        bookList.forEach(book -> bookDtoList.add(BookDto.getBookDto(book)));
        return bookDtoList;
    }

    @Override
    public BookDto modifyBook(Integer id, BookDto newBook) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if(!optionalBook.isPresent())return null;

        Book book = optionalBook.get();

        book.setId(newBook.getId());
        book.setTitle(newBook.getTitle());
        book.setNumberOfBooks(newBook.getNumberOfBooks());
        book.setPublication(newBook.getPublication());
        book.setAuthorId(newBook.getAuthorId());
        book.setAuthorName(newBook.getAuthorName());

        return BookDto.getBookDto(bookRepository.save(book));
    }

    @Override
    public void removeBook(Integer id) throws BookNotFoundException {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (!optionalBook.isPresent()) throw new BookNotFoundException(id);
        bookRepository.deleteAllById(Collections.singleton(id));
    }


    @Override
    public List<BookDto> getBookByAuthorId(Integer authorId) {
        List<Book> bookList = bookRepository.findByAuthorId(authorId);

        if(bookList.isEmpty()) return null;

        List<BookDto> bookDtoList = new ArrayList<BookDto>();
        bookList.forEach(book -> bookDtoList.add(BookDto.getBookDto(book)));

        return bookDtoList;
    }

    @Override
    public List<BookDto> findBookByTitle(String bookTitle) throws BookNotFoundException {
        List<Book> bookList = bookRepository.findByTitle(bookTitle);

        if(bookList.isEmpty()) return null;

        List<BookDto> bookDtoList = new ArrayList<BookDto>();
        bookList.forEach(book -> bookDtoList.add(BookDto.getBookDto(book)));
        return bookDtoList;
    }


    /**
     * @param bookId
     * @param customerId
     */
    @Override
    public void borrowBook(Integer bookId, Integer customerId) {
//                Customer customer = getUserService.getById(userId);
//        if(customer.getBorrowedBooks().stream()
//                .anyMatch(book -> book.getId()== bookId)){
//            throw new IllegalStateException("User already borrowed " +
//                    "the book");
//        }
//
//        if(customer.getBorrowedBooks().size() >= 3){
//            throw new IllegalStateException("User already has " +
//                    "maximum number of books borrowed!");
//        }
//
//        Book book = bookRepository.findById(bookId)
//                .orElseThrow(() -> new BookNotFoundException(bookId));
//
//        if(book.getNumberOfBooks()-1 < 0){
//            throw new IllegalStateException("There are no available" +
//                    " books!");
//        }
//
//        book.getUsers().add(customer);
//        book.setNumberOfBooks(book.getNumberOfBooks()+1);
//        bookRepository.save(book);

    }

}
