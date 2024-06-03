package net.bookshopproject.articleservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "commentuser")
public class User {
    @Id
    private long userId;
    private String firstname;
    private String lastname;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Article> articles;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Comment> comments;

    public User(long userId, String firstname, String lastname) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
    }
}
