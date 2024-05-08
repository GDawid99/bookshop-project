package net.bookshopproject.bookservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

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
    private Integer rating;
    private String imageUrl;
}
