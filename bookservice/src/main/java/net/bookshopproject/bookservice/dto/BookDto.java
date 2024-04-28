package net.bookshopproject.bookservice.dto;

import lombok.Data;
import net.bookshopproject.bookservice.model.Author;

import java.sql.Date;

@Data
public class BookDto {
    private Long book_id;
    private String title;
    private Author author;
    private String publisher;
    private Date dateOfPublication;
    private String genre;
    private String price;
    private Integer quantity;
    private Integer rating;
    private String imageUrl;
}
