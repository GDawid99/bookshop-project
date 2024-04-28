package net.bookshopproject.bookservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    @JsonManagedReference
    @JsonIgnore
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
