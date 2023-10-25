package com.ecommerce.project.com.ecommerce.project.jar.repository;


import com.ecommerce.project.com.ecommerce.project.jar.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface CartRepository extends JpaRepository<Cart, Long> {

}
