package net.bookshopproject.bookservice.repository;

import net.bookshopproject.bookservice.model.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitle(String title);

    @Query("select b from Book b")
    List<Book> findAllBooks(Pageable page);
}
