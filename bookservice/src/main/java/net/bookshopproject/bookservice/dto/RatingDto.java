package net.bookshopproject.bookservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingDto {
    private long ratingId;
    private BookDto book;
    private long userId;
    private String body;
    private int rating;
    private String name;
}
