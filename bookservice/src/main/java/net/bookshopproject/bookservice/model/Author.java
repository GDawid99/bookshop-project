package net.bookshopproject.bookservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="author")
public class Author {
    @Id
    @GeneratedValue
    private Long author_id;
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<Book> bookList;
    private String firstname;
    private String lastname;
    private Integer rating;

    public Author(long author_id, String firstname, String lastname, int rating) {
        this.author_id = author_id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.rating = rating;
        bookList = new ArrayList<>();
    }
}
