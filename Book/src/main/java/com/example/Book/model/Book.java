package com.example.Book.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "authorId")
    private Integer authorId;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "publication")
    private Date publication;

    @NotBlank(message = "mobileNumber is required")
    @Min(value = 1, message="must be equal or greater than 1")
    @Column(name = "numberOfInstances", nullable = false)
    private int numberOfBooks;

//    @ManyToMany
//    @JoinTable(
//            name = "books_borrowed",
//            joinColumns = @JoinColumn(name = "customer_id"),
//            inverseJoinColumns = @JoinColumn(name = "books_id")
//    )
//    private List<CustomerBooks> booksBorrowed;
//
//    @ManyToMany
//    @JoinTable(
//            name = "books_borrowed",
//            joinColumns = @JoinColumn(name = "customer_id"),
//            inverseJoinColumns = @JoinColumn(name = "books_id")
//    )
//    private List<CustomerBooks> booksBorrowed;

}