package net.bookshopproject.bookservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="book")
public class Book {
    @Id
    @GeneratedValue
    private Long book_id;
    private String title;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="author_id")
    private Author author;
    private String publisher;
    private Date dateOfPublication;
    private String genre;
    private String price;
    private Integer quantity;
    private Float rating;
    private String imageUrl;
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private List<Rating> ratingList;

    public Book(Long book_id, String title, Author author, String publisher, Date dateOfPublication, String genre, String price, Integer quantity, Float rating, String imageUrl) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.dateOfPublication = dateOfPublication;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.ratingList = new ArrayList<>();
    }
}
