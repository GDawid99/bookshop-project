package net.bookshopproject.articleservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue
    private long article_id;
    private String title;
    @Column(length = 4000)
    private String body;
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    private List<Comment> commentList;
    private LocalDate dateOfPublication;
}
