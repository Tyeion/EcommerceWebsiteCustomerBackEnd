package com.ecommerce.project.com.ecommerce.project.jar.repository;

import com.ecommerce.project.com.ecommerce.project.jar.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Repository

public interface CustomerRepository extends JpaRepository<Customer, Long> {


    @Query("SELECT c FROM Customer c WHERE c.username = :name")
    public Customer findByName(@Param("name") String username);

    Customer findByemailAndPassword(String email, String password);
}