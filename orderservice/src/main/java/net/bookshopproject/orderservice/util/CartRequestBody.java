package net.bookshopproject.orderservice.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bookshopproject.orderservice.dto.CartElementDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestBody {
    private long user_id;
    private List<CartElementDto> elements;
}
