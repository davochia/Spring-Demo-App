package com.example.Author.repository;

import com.example.Author.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Author s " +
            "WHERE s.email = ?1 OR s.phone = ?2"
    )
    Boolean selectExistsEmailPhone(String email, String phone);

    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Author s " +
            "WHERE s.email = ?1 "
    )
    Boolean selectExistsEmail(String email);

    @Query("" +
            "SELECT CASE WHEN COUNT(s) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Author s " +
            "WHERE s.phone = ?1 "
    )
    Boolean selectExistsPhone(String phone);
}
