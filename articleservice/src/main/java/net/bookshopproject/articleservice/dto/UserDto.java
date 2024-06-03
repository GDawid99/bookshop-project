package net.bookshopproject.articleservice.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bookshopproject.articleservice.model.Article;
import net.bookshopproject.articleservice.model.Comment;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long userId;
    private String firstname;
    private String lastname;
    private List<ArticleDto> articles;
    private List<CommentDto> comments;
}
