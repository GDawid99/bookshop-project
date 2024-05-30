package net.bookshopproject.orderservice.controller;

import net.bookshopproject.orderservice.dto.CartDto;
import net.bookshopproject.orderservice.dto.CartElementDto;
import net.bookshopproject.orderservice.service.CartService;
import net.bookshopproject.orderservice.util.CartRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> createNewOrder(@RequestBody CartRequestBody cartRequestBody) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(cartService.createNewOrder(cartRequestBody));
    }

    @PutMapping("/status/{cart_id}/{status}")
    public ResponseEntity<String> changeCartStatus(@PathVariable long cart_id, @PathVariable String status) {
        return ResponseEntity.ok(cartService.updateStatusOfOrder(cart_id,status));
    }
}
