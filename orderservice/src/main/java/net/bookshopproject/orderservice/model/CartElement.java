package net.bookshopproject.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart_element")
public class CartElement {
    @Id
    @GeneratedValue
    private long cartElement_id;
    private long cart_id;
    private long book_id;
    private int quantity;
}
