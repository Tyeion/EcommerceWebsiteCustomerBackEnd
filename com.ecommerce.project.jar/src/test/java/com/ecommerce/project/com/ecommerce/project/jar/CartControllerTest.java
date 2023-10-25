package com.ecommerce.project.com.ecommerce.project.jar;
import com.ecommerce.project.com.ecommerce.project.jar.controller.CartController;
import com.ecommerce.project.com.ecommerce.project.jar.model.Cart;
import com.ecommerce.project.com.ecommerce.project.jar.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddToCart() {
        Cart cartItem = new Cart();

        // Mock the behavior of the cartService's addToCart method
        when(cartService.addToCart(cartItem)).thenReturn(cartItem);

        // Call the controller method
        ResponseEntity<Cart> response = cartController.addToCart(cartItem);

        // Verify the response and status code
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(cartItem, response.getBody());
    }

    @Test
    void testGetCartItems() {
        List<Cart> cartItems = new ArrayList<>();

        // Mock the behavior of the cartService's getCartItems method
        when(cartService.getCartItems()).thenReturn(cartItems);

        // Call the controller method
        ResponseEntity<List<Cart>> response = cartController.getCartItems();

        // Verify the response and status code
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cartItems, response.getBody());
    }

    @Test
    void testRemoveFromCart() {
        Long cartItemId = 1L;

        // Call the controller method to remove an item from the cart
        ResponseEntity<Void> response = cartController.removeFromCart(cartItemId);

        // Verify the response status code
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verify that the cartService's removeFromCart method was called
        verify(cartService, times(1)).removeFromCart(cartItemId);
    }






}
