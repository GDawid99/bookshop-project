package net.bookshopproject.orderservice.service;

import net.bookshopproject.orderservice.dto.CartElementDto;
import net.bookshopproject.orderservice.mapper.Mapper;
import net.bookshopproject.orderservice.model.Cart;
import net.bookshopproject.orderservice.model.CartElement;
import net.bookshopproject.orderservice.repository.CartElementRepository;
import net.bookshopproject.orderservice.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CartElementService {

    @Autowired
    CartElementRepository cartElementRepository;

    @Autowired
    CartRepository cartRepository;

    public List<CartElementDto> findAllElementsFromCart(long id) {
        Cart cart = cartRepository.getReferenceById(id);
        List<CartElement> cartElementList = cartElementRepository.findByCart(cart);
        List<CartElementDto> cartElementDtoList = new ArrayList<>();
        for (CartElement ce: cartElementList) {
            cartElementDtoList.add(Mapper.mapFromCartElementToDto(ce));
        }
        return cartElementDtoList;
    }

}
