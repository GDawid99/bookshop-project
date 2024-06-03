package net.bookshopproject.orderservice.service;

import net.bookshopproject.orderservice.Status;
import net.bookshopproject.orderservice.dto.CartElementDto;
import net.bookshopproject.orderservice.model.Cart;
import net.bookshopproject.orderservice.model.CartElement;
import net.bookshopproject.orderservice.repository.CartElementRepository;
import net.bookshopproject.orderservice.repository.CartRepository;
import net.bookshopproject.orderservice.util.CartRequestBody;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    AmqpTemplate amqpTemplate;

    @Autowired
    CartElementRepository cartElementRepository;

    public String createNewOrder(CartRequestBody cartRequestBody) {
        Cart cart = cartRepository.save(new Cart(cartRequestBody.getUser_id(), new ArrayList<>(), LocalDate.now(), Status.NEW.name()));

        for (CartElementDto dto: cartRequestBody.getElements()) {
            cartElementRepository.save(new CartElement(cart,dto.getBook_id(),dto.getQuantity()));
        }
        amqpTemplate.convertAndSend("notification-exchange", "key.order", "STATUS:" + cart.getStatus() + ";CART_ID:" + cart.getCartId() + ";USER_ID:" + cart.getUserId());
        return "New order created.";
    }

    public String updateStatusOfOrder(long cart_id, String status) {
        Cart cart = cartRepository.findById(cart_id).map(newCart -> {
            newCart.setStatus(status);
            return cartRepository.save(newCart);
        }).orElseThrow();
        amqpTemplate.convertAndSend("notification-exchange","key.order","STATUS:" + status + ";CART_ID:" + cart_id + ";USER_ID:" + cart.getUserId());
        return "Status of cart id " + cart_id + " has been changed to " + status;
    }

}
