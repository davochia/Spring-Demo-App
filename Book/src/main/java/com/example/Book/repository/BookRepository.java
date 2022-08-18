package com.example.Book.repository;

import com.example.Book.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query(nativeQuery = true, value = "SELECT * FROM Book book WHERE book.title = ?1 ")
    List<Book> findByTitle(String title);

    @Query(value = "SELECT * FROM Book b WHERE b.author_id = ?1", nativeQuery = true)
    List<Book> findByAuthorId(Integer authorId);
}
