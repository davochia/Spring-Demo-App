package com.example.Author.dto;


import com.example.Author.model.Author;
import lombok.Data;

@Data
public class AuthorDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;


    public static Author getAuthor(AuthorDto authorDto){
        Author author = new Author();
        author.setId(authorDto.getId());
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setPhone(authorDto.getPhone());
        author.setEmail(authorDto.getEmail());

        return author;
    }

    public static AuthorDto getAuthorDto(Author author){
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(author.getId());
        authorDto.setFirstName(author.getFirstName());
        authorDto.setLastName(author.getLastName());
        authorDto.setPhone(author.getPhone());
        authorDto.setEmail(author.getEmail());

        return authorDto;
    }

}
