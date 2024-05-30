package net.bookshopproject.articleservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "articlecomment")
public class Comment {
    @Id
    @GeneratedValue
    private long comment_id;
    private long user_id;
    private String body;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "article_id")
    private Article article;
    private LocalDate dateOfPublication;

}
