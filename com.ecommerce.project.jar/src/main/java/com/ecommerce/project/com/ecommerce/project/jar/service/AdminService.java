package com.ecommerce.project.com.ecommerce.project.jar.service;

import com.ecommerce.project.com.ecommerce.project.jar.model.Admin;
import com.ecommerce.project.com.ecommerce.project.jar.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public Admin adminlogin(String email, String password){
        return adminRepository.findByemailAndPassword(email,password);
    }


    public Admin addAdmin(Admin admin){
        return adminRepository.save(admin);
    }






}
