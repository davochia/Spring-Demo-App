package com.example.Author.model;

import javax.persistence.*;

@Entity(name = "authorBooks")
public class AuthorBooks {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name= "bookTitle")
    private String bookTitle;

    @Column(name= "bookId")
    private String bookId;

    @ManyToOne
    @JoinColumn(name="author_id", nullable=false)
    private Author author;
}
