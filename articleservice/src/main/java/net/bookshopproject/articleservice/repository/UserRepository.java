package net.bookshopproject.articleservice.repository;

import net.bookshopproject.articleservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
