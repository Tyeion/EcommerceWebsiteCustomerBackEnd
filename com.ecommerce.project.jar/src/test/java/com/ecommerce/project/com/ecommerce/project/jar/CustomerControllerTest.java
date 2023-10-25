package com.ecommerce.project.com.ecommerce.project.jar;
import com.ecommerce.project.com.ecommerce.project.jar.controller.CustomerController;
import com.ecommerce.project.com.ecommerce.project.jar.model.Customer;
import com.ecommerce.project.com.ecommerce.project.jar.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }





    @Test
    void testGetCustomerByIdNotFound() {
        Long customerId = 2L;
        when(customerService.getCustomerById(customerId)).thenReturn(null);

        ResponseEntity<Customer> response = customerController.getCustomerById(customerId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testDeleteCustomer() {
        Long customerId = 1L;

        ResponseEntity<Customer> response = customerController.deleteCustomer(customerId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(customerService, times(1)).deleteCustomerById(customerId);
    }

    @Test
    void testAddCustomer() {
        Customer customer = new Customer();

        ResponseEntity<Customer> response = customerController.addCustomer(customer);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(customerService, times(1)).addCustomer(customer);
    }

    @Test
    void testUpdateCustomerFound() {
        Long customerId = 1L;
        Customer updatedCustomer = new Customer();
        when(customerService.updateCustomer(customerId, updatedCustomer)).thenReturn(updatedCustomer);

        ResponseEntity<Customer> response = customerController.updateCustomer(customerId, updatedCustomer);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(updatedCustomer, response.getBody());
    }

    @Test
    void testUpdateCustomerNotFound() {
        Long customerId = 2L;
        Customer updatedCustomer = new Customer();
        when(customerService.updateCustomer(customerId, updatedCustomer)).thenReturn(null);

        ResponseEntity<Customer> response = customerController.updateCustomer(customerId, updatedCustomer);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testValidateValid() {
        String email = "test@example.com";
        String password = "password";
        Customer customer = new Customer();
        when(customerService.login(email, password)).thenReturn(customer);

        Customer response = customerController.validate(email, password);

        assertSame(customer, response);
    }

    @Test
    void testValidateInvalid() {
        String email = "test@example.com";
        String password = "password";
        when(customerService.login(email, password)).thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> customerController.validate(email, password));
    }

    @Test
    void testGetAllCustomer() {
        List<Customer> customers = new ArrayList<>();
        when(customerService.getAllCustomer()).thenReturn(customers);

        ResponseEntity<List<Customer>> response = customerController.getAllCustomer();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(customers, response.getBody());
    }

    @Test
    void testGetCustomerByIdFound() {
        Long customerId = 1L;
        Customer customer = new Customer();
        when(customerService.getCustomerById(customerId)).thenReturn(customer);

        ResponseEntity<Customer> response = customerController.getCustomerById(customerId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertSame(customer, response.getBody());
    }

    @Test
    void testAddCustomerException() {
        Customer customer = new Customer();
        doThrow(ResponseStatusException.class).when(customerService).addCustomer(customer);

        assertThrows(ResponseStatusException.class, () -> customerController.addCustomer(customer));
    }


    @Test
    void testUpdateCustomerException() {
        Long customerId = 1L;
        Customer updatedCustomer = new Customer();
        doThrow(ResponseStatusException.class).when(customerService).updateCustomer(customerId, updatedCustomer);

        assertThrows(ResponseStatusException.class, () -> customerController.updateCustomer(customerId, updatedCustomer));
    }

    @Test
    void testValidateException() {
        String email = "test@example.com";
        String password = "password";
        doThrow(ResponseStatusException.class).when(customerService).login(email, password);

        assertThrows(ResponseStatusException.class, () -> customerController.validate(email, password));
    }

    @Test
    void testAddCustomerInvalidData() {
        // Create a customer with incomplete data
        Customer customer = new Customer();

        // Mock the service to throw a ResponseStatusException
        doThrow(ResponseStatusException.class).when(customerService).addCustomer(customer);

        assertThrows(ResponseStatusException.class, () -> customerController.addCustomer(customer));
    }

    @Test
    void testUpdateCustomerInvalidData() {
        // Create a customer with invalid data
        Customer customer = new Customer();

        // Mock the service to throw a ResponseStatusException
        doThrow(ResponseStatusException.class).when(customerService).updateCustomer(anyLong(), eq(customer));

        assertThrows(ResponseStatusException.class, () -> customerController.updateCustomer(1L, customer));
    }

    @Test
    void testDeleteNonExistentCustomer() {
        // Provide an ID for a customer that does not exist
        Long customerId = 999L;

        // Mock the service to throw a ResponseStatusException
        doThrow(ResponseStatusException.class).when(customerService).deleteCustomerById(customerId);

        assertThrows(ResponseStatusException.class, () -> customerController.deleteCustomer(customerId));
    }

    @Test
    void testGetCustomerByNegativeID() {
        // Provide a negative customer ID
        Long customerId = -1L;

        ResponseEntity<Customer> response = customerController.getCustomerById(customerId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void testAddCustomerWithExistingEmail() {
        // Create a customer with an email that already exists
        Customer customer = new Customer();
        customer.setEmail("test@example.com");

        // Mock the service to throw a ResponseStatusException
        doThrow(ResponseStatusException.class).when(customerService).addCustomer(customer);

        assertThrows(ResponseStatusException.class, () -> customerController.addCustomer(customer));
    }

    @Test
    void testValidateInvalidEmailAndPassword() {
        String email = "nonexistent@example.com";
        String password = "invalidPassword";

        // Mock the service to return null for validation
        when(customerService.login(email, password)).thenReturn(null);

        assertThrows(ResponseStatusException.class, () -> customerController.validate(email, password));
    }

    @Test
    void testGetAllCustomerEmptyList() {
        List<Customer> customers = new ArrayList<>();
        when(customerService.getAllCustomer()).thenReturn(customers);

        ResponseEntity<List<Customer>>response = customerController.getAllCustomer();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isEmpty());
    }





}
