package net.bookshopproject.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
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
    private long cart_id;
    private long user_id;
    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private List<CartElement> cartElementList;
    private Date date;
}
