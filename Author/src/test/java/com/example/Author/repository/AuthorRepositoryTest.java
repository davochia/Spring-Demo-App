package com.example.Author.repository;

import com.example.Author.model.Author;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepositoryTest;

    @AfterEach
    void tearDown() {
        authorRepositoryTest.deleteAll();
    }


    @Test
    void itShouldCheckAuthorEmailPhoneExists() {
        String email = "johndoe@example.com";
        String phone = "01234567890";

        Author author = new Author(0,"John", "Doe", phone, email,null);

//        authorRepositoryTest.save(author);
//
//        boolean expected = authorRepositoryTest.selectExistsEmailPhone(email, phone);
//
//        assertThat(expected).isTrue();
    }

    @Test
    void itShouldCheckAuthorEmailPhoneDoesNotExists() {
        String email = "johndoe@example.com";
        String phone = "01234567890";

        boolean expected = authorRepositoryTest.selectExistsEmailPhone(email, phone);

        assertThat(expected).isFalse();
    }

    @Test
    void itShouldCheckAuthorEmailDoesNotExists() {
        String email = "johndoe@example.com";

        boolean expected = authorRepositoryTest.selectExistsEmail(email);

        assertThat(expected).isFalse();
    }

    @Test
    void itShouldCheckAuthorPhoneDoesNotExists() {
        String phone = "01234567890";

        boolean expected = authorRepositoryTest.selectExistsPhone( phone);

        assertThat(expected).isFalse();
    }



}