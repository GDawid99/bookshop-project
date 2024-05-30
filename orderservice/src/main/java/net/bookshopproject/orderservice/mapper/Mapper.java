package net.bookshopproject.orderservice.mapper;

import net.bookshopproject.orderservice.dto.CartDto;
import net.bookshopproject.orderservice.dto.CartElementDto;
import net.bookshopproject.orderservice.model.Cart;
import net.bookshopproject.orderservice.model.CartElement;

public class Mapper {
    public static CartElement mapFromDtoToCartElement(CartElementDto cartElementDto) {
        CartElement cartElement = new CartElement();
        cartElement.setCartElementId(cartElementDto.getCartElement_id());
        cartElement.setCart(mapFromDtoToCart(cartElementDto.getCart()));
        cartElement.setBookId(cartElementDto.getBook_id());
        cartElement.setQuantity(cartElementDto.getQuantity());
        return cartElement;
    }

    public static CartElementDto mapFromCartElementToDto(CartElement cartElement) {
        CartElementDto cartElementDto = new CartElementDto();
        cartElementDto.setCartElement_id(cartElement.getCartElementId());
        cartElementDto.setCart(mapFromCartToDto(cartElement.getCart()));
        cartElementDto.setBook_id(cartElement.getBookId());
        cartElementDto.setQuantity(cartElement.getQuantity());
        return cartElementDto;
    }

    public static Cart mapFromDtoToCart(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setCartId(cartDto.getCart_id());
        cart.setUserId(cartDto.getUser_id());
        cart.setDate(cartDto.getDate());
        cart.setStatus(cartDto.getStatus());
        return cart;
    }

    public static CartDto mapFromCartToDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setCart_id(cart.getCartId());
        cartDto.setUser_id(cart.getUserId());
        cartDto.setDate(cart.getDate());
        cartDto.setStatus(cart.getStatus());
        return cartDto;
    }
}
