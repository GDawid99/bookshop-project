package net.bookshopproject.articleservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "article")
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long article_id;
    private String title;
    @Column(length = 4000)
    private String body;
    @OneToMany(mappedBy = "article", fetch = FetchType.EAGER)
    private List<Comment> commentList;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId")
    private User user;
    private LocalDate dateOfPublication;

    public Article(long article_id, User user, String title, String body, LocalDate dateOfPublication) {
        this.article_id = article_id;
        this.title = title;
        this.body = body;
        this.user = user;
        this.dateOfPublication = dateOfPublication;
    }
}
