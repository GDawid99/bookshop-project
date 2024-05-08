package net.bookshopproject.orderservice.dto;


import lombok.Data;

@Data
public class AuthorDto {
    private Long author_id;
    private String firstname;
    private String lastname;
    private Integer rating;
}
