package net.bookshopproject.bookservice.repository;

import net.bookshopproject.bookservice.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Author findByFirstnameAndLastname(String firstname, String lastname);
}
