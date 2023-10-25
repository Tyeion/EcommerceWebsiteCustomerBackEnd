package com.ecommerce.project.com.ecommerce.project.jar.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
@Table(name ="Customer_details")
public class Customer {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name ="username")
    private String username;

    @NotNull
    private String password;

    private Long mobileNo;

    private String email;

    public Customer(){

    }

    public Customer( String username, String password, String firstname, String lastname, Long mobileNo, String email) {

        this.username = username;
        this.password = password;
        this.mobileNo = mobileNo;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(Long mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
