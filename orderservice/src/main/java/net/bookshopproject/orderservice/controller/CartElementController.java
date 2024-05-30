package net.bookshopproject.orderservice.controller;

import net.bookshopproject.orderservice.dto.CartElementDto;
import net.bookshopproject.orderservice.service.CartElementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/cart/{id}")
    public ResponseEntity<String> addCartElements(@PathVariable long cart_id, @RequestBody List<CartElementDto> elements) {
        cartElementService.addCartElements(elements, cart_id);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).build();
    }

}
