package com.ecommerce.project.com.ecommerce.project.jar.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name ="Admin_table")
public class Admin {
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

    public Admin(){

    }

    public Admin( String username, String password, Long mobileNo, String email) {

        this.username = username;
        this.password = password;
        this.mobileNo = mobileNo;
        this.email = email;
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
