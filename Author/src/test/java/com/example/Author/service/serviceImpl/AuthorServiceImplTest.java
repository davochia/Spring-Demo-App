package com.example.Author.service.serviceImpl;

import com.example.Author.dto.AuthorDto;
import com.example.Author.exception.AuthorFoundException;
import com.example.Author.exception.AuthorNotFoundException;
import com.example.Author.model.Author;
import com.example.Author.repository.AuthorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorServiceImpl authorServiceImplTest;

    @Captor
    private ArgumentCaptor<AuthorDto> captor;

    @Test
	public void contextLoads() {
		assertThat(authorServiceImplTest).isNotNull();
	}

    @BeforeEach
    void setUp() {
        authorServiceImplTest = new AuthorServiceImpl(authorRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
    }


    @Test
    void addAuthor() {
        AuthorDto authorDto = AuthorDto.getAuthorDto(new Author( 1,"John", "Doe", "01234567890", "johndoe@example.com",null));

//        ArgumentCaptor<AuthorDto> authorArgumentCaptor = ArgumentCaptor.forClass(AuthorDto.class);
//        authorServiceImplTest.addAuthor(authorDto);
//        verify(authorRepository).save(AuthorDto.getAuthor(authorArgumentCaptor.capture())) ;
//        AuthorDto capturedAuthor = authorArgumentCaptor.getValue();

        assertThat( authorServiceImplTest.addAuthor(authorDto)).isEqualTo(authorDto);
    }

    @Test
    void shouldCheckDetails() {
        AuthorDto authorDto = AuthorDto.getAuthorDto(new Author( 1,"John", "Doe", "01234567890", "johndoe@example.com",null));

        AuthorDto returnedAuthor = authorServiceImplTest.addAuthor(authorDto);
        assertEquals("John", returnedAuthor.getFirstName());
        assertEquals("Doe", returnedAuthor.getLastName());
        assertEquals("01234567890", returnedAuthor.getPhone());
        assertEquals("johndoe@example.com", returnedAuthor.getEmail());
    }


    @Test
    void getAuthors() {
        authorServiceImplTest.getAuthors();
        verify(authorRepository).findAll();
    }


    @Test
    void willThrowWhenEmailIsTaken() {
        AuthorDto authorDto = AuthorDto.getAuthorDto(new Author( 0,"John", "Doe", "01234567890", "johndoe@example.com",null));
        given(authorRepository.selectExistsEmail(anyString())).willReturn(true);

        assertThatThrownBy(() -> {
            authorServiceImplTest.addAuthor(authorDto);
        })
                .isInstanceOf(AuthorFoundException.class).hasMessageContaining("Email or Phone is already in use");

        verify(authorRepository, never()).save(any());
    }

    @Test
    void willThrowWhenPhoneIsTaken() {
        AuthorDto authorDto = AuthorDto.getAuthorDto(new Author( 0,"John", "Doe", "01234567890", "johndoe@example.com",null));
        given(authorRepository.selectExistsPhone(authorDto.getPhone())).willReturn(true);

        assertThatThrownBy(() -> {
            authorServiceImplTest.addAuthor(authorDto);
        })
                .isInstanceOf(AuthorFoundException.class).hasMessageContaining("Email or Phone is already in use");

        verify(authorRepository, never()).save(any());
    }

    @Test
    void willThrowWhenDeleteAuthorNotFound() {
        Integer id = 30;

        given(authorRepository.findById(id))
                .willReturn(null);

        assertThatThrownBy(() -> authorServiceImplTest.removeAuthor(id))
                .isInstanceOf(AuthorNotFoundException.class)
                .hasMessageContaining("Could not find author with id: " + id);
        verify(authorRepository, never()).deleteById(any());

    }

    @Test
    void shouldCheckMultiInsertAndDetails() {
        AuthorDto authorDto1 = AuthorDto.getAuthorDto(new Author( 1,"John", "Doe", "01234567891", "johndoe@example.com",null));
        AuthorDto authorDto2 = AuthorDto.getAuthorDto(new Author( 2,"Mike", "Dav", "01234567892", "mikedav@example.com",null));
        AuthorDto authorDto3 = AuthorDto.getAuthorDto(new Author( 3,"James", "Bond", "01234567893", "jamesbond@example.com",null));
        AuthorDto authorDto4 = AuthorDto.getAuthorDto(new Author( 4,"Sam", "Jack", "01234567894", "samjack@example.com",null));

//
//        when(authorServiceImplTest.addAuthor(authorDto1)).thenReturn(authorDto1);
//        when(authorServiceImplTest.addAuthor(authorDto2)).thenReturn(authorDto2);
//        when(authorServiceImplTest.addAuthor(authorDto3)).thenReturn(authorDto3);
//        when(authorServiceImplTest.addAuthor(authorDto4)).thenReturn(authorDto4);


//        authorServiceImplTest.addAuthor(authorDto1);
//        authorServiceImplTest.addAuthor(authorDto2);
//        authorServiceImplTest.addAuthor(authorDto3);
//        authorServiceImplTest.addAuthor(authorDto4);

//        Mockito.verify(authorServiceImplTest,Mockito.times(4)).addAuthor(AuthorDto.getAuthor( captor.capture()));
//
//        AuthorDto returnedAuthor1 = authorServiceImplTest.addAuthor(authorDto1);
//        AuthorDto returnedAuthor2 = authorServiceImplTest.addAuthor(authorDto2);
//        AuthorDto returnedAuthor3 = authorServiceImplTest.addAuthor(authorDto3);
//        AuthorDto returnedAuthor4 = authorServiceImplTest.addAuthor(authorDto4);

//        assertThat(returnedAuthor1, hasProperty( "firstName", equalTo("John")));
//
//        assertEquals("John", returnedAuthor1.getFirstName());
//        assertEquals("Dav", returnedAuthor2.getLastName());
//        assertEquals("01234567893", returnedAuthor3.getPhone());
//        assertEquals("samjack@example.com", returnedAuthor4.getEmail());
    }






    @Test
    void removeAuthor() {
        Integer id = 23;

//        given(authorRepository.existsById(id))
//                .willReturn(true);
//
//        authorServiceImplTest.removeAuthor(id);
//
//        verify(authorRepository).deleteById(id);
    }


    @Test
    void modifyAuthor() {
    }

    @Test
    void findAuthorById() {
    }

    @Test
    void getAuthorBooks() {
    }
}