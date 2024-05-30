package net.bookshopproject.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue
    @Column(name = "cart_id")
    private long cartId;
    @Column(name = "user_id")
    private long userId;
    @OneToMany(mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartElement> cartElementList;
    private LocalDate date;
    private String Status;

    public Cart(long userId, List<CartElement> cartElementList, LocalDate date, String status) {
        this.userId = userId;
        this.cartElementList = cartElementList;
        this.date = date;
        Status = status;
    }
}
