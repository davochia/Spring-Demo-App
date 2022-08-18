package com.example.Customer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "Customer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name= "firstname")
    private String firstName;

    @Column(name= "lastname")
    private String lastName;

    @Column(name= "username")
    private String username;

    @Column(name= "phone", unique = true)
    private String phone;

    @Column(name= "email",unique = true)
    private String email;

    @Column(name= "DOB", nullable = true)
    private Date dataOfBirth;

    @ManyToMany
    @JoinTable(
            name = "books_borrowed",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "books_id")
    )
    private List<CustomerBooks> booksBorrowed;

}
