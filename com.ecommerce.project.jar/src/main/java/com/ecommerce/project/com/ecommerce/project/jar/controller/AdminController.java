package com.ecommerce.project.com.ecommerce.project.jar.controller;

import com.ecommerce.project.com.ecommerce.project.jar.model.Admin;
import com.ecommerce.project.com.ecommerce.project.jar.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/validating")
    public Admin validating(@RequestParam String email, @RequestParam String password){
        Admin admin= adminService.adminlogin(email,password);
        if(admin!=null){
            return admin;
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Please enter Valid Details");
        }
    }

    @PostMapping("/newAdmin")
    public ResponseEntity<Admin> addAdmmin(@RequestBody Admin admin){
        adminService.addAdmin(admin);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
