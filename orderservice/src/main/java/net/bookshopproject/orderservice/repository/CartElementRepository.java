package net.bookshopproject.orderservice.repository;

import net.bookshopproject.orderservice.model.CartElement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartElementRepository extends JpaRepository<CartElement,Long> {
}
