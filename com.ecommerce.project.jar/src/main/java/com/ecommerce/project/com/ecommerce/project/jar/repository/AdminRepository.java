package com.ecommerce.project.com.ecommerce.project.jar.repository;

import com.ecommerce.project.com.ecommerce.project.jar.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

    Admin findByemailAndPassword(String email,String password);
}
