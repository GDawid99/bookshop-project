package net.bookshopproject.orderservice.controller;

import net.bookshopproject.orderservice.dto.CartElementDto;
import net.bookshopproject.orderservice.service.CartElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cartelement")
public class CartElementController {

    @Autowired
    CartElementService cartElementService;

    @GetMapping("/cart/{id}")
    public ResponseEntity<List<CartElementDto>> findAllProductsByCart(@PathVariable long id) {
        return ResponseEntity.ok(cartElementService.findAllElementsFromCart(id));
    }

}
