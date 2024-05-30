package net.bookshopproject.orderservice.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class CartDto {
    private long cart_id;
    private long user_id;
    private List<CartElementDto> cartElementList;
    private LocalDate date;
    private String status;
}
