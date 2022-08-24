package com.example.Author.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name= "firstname")
    private String firstName;

    @Column(name= "lastname")
    private String lastName;

    @Column(name= "phone", unique = true)
    private String phone;

    @Column(name= "email",unique = true)
    private String email;

    @OneToMany(mappedBy = "author")
    private List<AuthorBooks> authorBooksList;

}
