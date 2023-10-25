package com.ecommerce.project.com.ecommerce.project.jar.controller;

import com.ecommerce.project.com.ecommerce.project.jar.model.Cart;
import com.ecommerce.project.com.ecommerce.project.jar.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/Cart")
public class CartController {


    @Autowired
    private CartService cartService;



    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cartItem) {
        Cart addedCartItem = cartService.addToCart(cartItem);
        return new ResponseEntity<>(addedCartItem, HttpStatus.CREATED);
    }

    @GetMapping("/items")
    public ResponseEntity<List<Cart>> getCartItems() {
        List<Cart> cartItems = cartService.getCartItems();
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @DeleteMapping("/remove/{cartItemId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long cartItemId) {
        cartService.removeFromCart(cartItemId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);     }
}