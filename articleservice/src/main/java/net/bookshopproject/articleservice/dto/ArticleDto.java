package net.bookshopproject.articleservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bookshopproject.articleservice.model.Comment;
import net.bookshopproject.articleservice.model.User;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private long article_id;
    private String title;
    private String body;
    private List<CommentDto> commentList;
    private LocalDate dateOfPublication;
    private UserDto user;
}
