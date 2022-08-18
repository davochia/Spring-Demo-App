package com.example.Customer.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "books")
public class CustomerBooks {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name= "bookTitle")
    private String bookTitle;

    @Column(name= "bookId")
    private String bookId;

    @ManyToMany(mappedBy = "booksBorrowed")
    private List<Customer> customerList;
}
