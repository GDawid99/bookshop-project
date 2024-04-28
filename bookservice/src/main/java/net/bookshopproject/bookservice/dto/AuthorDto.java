package net.bookshopproject.bookservice.dto;

import lombok.Data;
import net.bookshopproject.bookservice.model.Book;

import java.util.List;

@Data
public class AuthorDto {
    private Long author_id;
    private List<Book> bookList;
    private String firstname;
    private String lastname;
    private Integer rating;
}
