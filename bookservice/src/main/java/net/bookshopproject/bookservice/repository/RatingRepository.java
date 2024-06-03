package net.bookshopproject.bookservice.repository;

import net.bookshopproject.bookservice.model.Author;
import net.bookshopproject.bookservice.model.Book;
import net.bookshopproject.bookservice.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    boolean existsRatingByBookAndUserId(Book book, long userId);

    List<Rating> findAllByBook(Book book);
}
