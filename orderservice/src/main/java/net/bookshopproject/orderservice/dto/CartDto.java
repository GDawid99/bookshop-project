package net.bookshopproject.orderservice.dto;

import java.sql.Date;
import java.util.List;

public class CartDto {
    private long cart_id;
    private long user_id;
    private List<CartElementDto> cartElementList;
    private Date date;
}
