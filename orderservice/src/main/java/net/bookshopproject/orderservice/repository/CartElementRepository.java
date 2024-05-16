package net.bookshopproject.orderservice.repository;

import net.bookshopproject.orderservice.model.Cart;
import net.bookshopproject.orderservice.model.CartElement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartElementRepository extends JpaRepository<CartElement,Long> {
    List<CartElement> findByCart(Cart cart);
}
