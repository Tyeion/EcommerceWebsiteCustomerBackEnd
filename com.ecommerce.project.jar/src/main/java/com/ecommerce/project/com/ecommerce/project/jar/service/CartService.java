package com.ecommerce.project.com.ecommerce.project.jar.service;

import com.ecommerce.project.com.ecommerce.project.jar.model.Cart;
import com.ecommerce.project.com.ecommerce.project.jar.repository.CartRepository;
import com.ecommerce.project.com.ecommerce.project.jar.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;



    @Autowired
    private CustomerRepository customerRepository;

    public Cart addToCart(Cart cart) {
        return cartRepository.save(cart);
    }     // Get items in the cart for a specific user (you may add user-specific logic)

    public List<Cart> getCartItems() {
        return cartRepository.findAll();
    }     // Remove an item from the cart

    public void removeFromCart(Long cartItemId) {
        Optional<Cart> cartItemOptional = cartRepository.findById(cartItemId);
        if (cartItemOptional.isPresent()) {
            cartRepository.deleteById(cartItemId);
        } else {
            throw new NoSuchElementException("Item not found in the cart");
        }
    }

}