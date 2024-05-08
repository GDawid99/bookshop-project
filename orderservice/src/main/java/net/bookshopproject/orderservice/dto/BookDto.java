package net.bookshopproject.orderservice.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class BookDto {
    private Long book_id;
    private String title;
    private AuthorDto author;
    private String publisher;
    private Date dateOfPublication;
    private String genre;
    private String price;
    private Integer quantity;
    private Integer rating;
    private String imageUrl;
}
