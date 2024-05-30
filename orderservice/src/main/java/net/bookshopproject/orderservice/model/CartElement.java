package net.bookshopproject.orderservice.model;

import jakarta.persistence.*;
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
    @Column(name = "cart_element_id")
    private long cartElementId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id")
    private Cart cart;
    @Column(name = "book_id")
    private long bookId;
    private int quantity;

    public CartElement(Cart cart, long bookId, int quantity) {
        this.cart = cart;
        this.bookId = bookId;
        this.quantity = quantity;
    }
}
