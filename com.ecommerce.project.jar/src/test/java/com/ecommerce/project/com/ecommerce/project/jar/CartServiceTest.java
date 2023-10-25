package com.ecommerce.project.com.ecommerce.project.jar;
import com.ecommerce.project.com.ecommerce.project.jar.model.Cart;
import com.ecommerce.project.com.ecommerce.project.jar.repository.CartRepository;
import com.ecommerce.project.com.ecommerce.project.jar.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddToCart() {
        // Create a Cart object to add to the cart
        Cart cart = new Cart();

        // Mock the behavior of the cartRepository's save method
        when(cartRepository.save(cart)).thenReturn(cart);

        // Call the service method to add to the cart
        Cart result = cartService.addToCart(cart);

        // Verify that the saved cart object is returned
        assertEquals(cart, result);
    }

    @Test
    void testGetCartItems() {
        // Mock the behavior of the cartRepository to return a list of cart items
        List<Cart> cartItems = new ArrayList<>();
        when(cartRepository.findAll()).thenReturn(cartItems);

        // Call the service method to retrieve cart items
        List<Cart> result = cartService.getCartItems();

        // Verify that the list of cart items is returned
        assertEquals(cartItems, result);
    }



    @Test
    void testRemoveFromCartNonExistentItem() {
        // Create a Cart item ID that doesn't exist in the repository
        Long nonExistentCartItemId = 999L;

        // Mock the behavior of the cartRepository's findById to return an empty Optional
        when(cartRepository.findById(nonExistentCartItemId)).thenReturn(Optional.empty());

        // Ensure that the service method handles the situation gracefully when trying to remove a non-existent item
        assertThrows(NoSuchElementException.class, () -> cartService.removeFromCart(nonExistentCartItemId));
    }


    @Test
    void testRemoveFromEmptyCart() {
        // Create a Cart item ID for removal
        Long cartItemId = 1L;

        // Mock the behavior of the cartRepository's findById to return an empty Optional
        when(cartRepository.findById(cartItemId)).thenReturn(Optional.empty());

        // Ensure that the service method handles the situation gracefully when trying to remove an item from an empty cart
        assertThrows(NoSuchElementException.class, () -> cartService.removeFromCart(cartItemId));
    }


}
