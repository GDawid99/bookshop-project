package net.bookshopproject.articleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bookshopproject.articleservice.model.Article;
import net.bookshopproject.articleservice.model.User;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    private long comment_id;
    private UserDto user;
    private String body;
    private ArticleDto article;
    private LocalDate dateOfPublication;
}
