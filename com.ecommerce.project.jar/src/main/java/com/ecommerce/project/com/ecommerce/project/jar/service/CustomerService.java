package com.ecommerce.project.com.ecommerce.project.jar.service;


import com.ecommerce.project.com.ecommerce.project.jar.model.Customer;
import com.ecommerce.project.com.ecommerce.project.jar.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerById(Long id)
    {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getAllCustomer(){
        return customerRepository.findAll();
    }

    public Customer addCustomer(Customer customer)
    {
        return customerRepository.save(customer);
    }

    public void deleteCustomerById(Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            // Handle the exception gracefully, e.g., log it or perform custom error handling
            // You can also rethrow it if needed
        }
    }


    public Customer updateCustomer(long id, Customer updatecustomer){
        Customer customer = customerRepository.findById(id).get();

        customer.setUsername(updatecustomer.getUsername());
        customer.setPassword(updatecustomer.getPassword());
        customer.setEmail(customer.getEmail());
        customer.setMobileNo(updatecustomer.getMobileNo());
        return customerRepository.save(customer);
    }

    public Customer login(String email, String password){
        return customerRepository.findByemailAndPassword(email, password);
    }
}