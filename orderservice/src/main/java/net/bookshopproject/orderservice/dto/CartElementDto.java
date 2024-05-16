package net.bookshopproject.orderservice.dto;


import lombok.Data;

@Data
public class CartElementDto {
    private long cartElement_id;
    private CartDto cart;
    private long book_id;
    private int quantity;
}
