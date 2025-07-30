package com.example.demo.controller;

import com.example.demo.dto.AddToCartRequest;
import com.example.demo.dto.OrderDto;
import com.example.demo.entity.Orders;
import com.example.demo.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("cart")
@CrossOrigin(origins = "http://localhost:4200")
public class CartController {

    private final CartService cartService;

    @PostMapping("addToCart")
    public ResponseEntity<String> addToCart(@RequestBody AddToCartRequest request) {
        return cartService.addToCart(request);
    }

    @GetMapping("getCartTickets/{userId}")
    public List<OrderDto> getCartTickets(@PathVariable int userId) {
        return cartService.getCartTickets(userId);
    }

    @GetMapping("getPurchasedTickets/{userId}")
    public List<OrderDto> getPurchasedTickets(@PathVariable int userId){
        return cartService.getPurchasedTickets(userId);
    }

    @DeleteMapping("deleteOrder/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable int orderId){
        return cartService.deleteOrder(orderId);
    }

    @PostMapping("purchaseOrder/{orderId}")
    public ResponseEntity<String> purchaseOrder(@PathVariable int orderId){
        return cartService.purchaseOrder(orderId);
    }


}
