package net.bookshopproject.userservice.repository;

import net.bookshopproject.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
