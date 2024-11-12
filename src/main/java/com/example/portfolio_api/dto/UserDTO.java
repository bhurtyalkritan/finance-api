// src/main/java/com/example/portfolio_api/dto/UserDTO.java

package com.example.portfolio_api.dto;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String accountNumber;
    private String phoneNumber;
    private String address;

    // Constructors
    public UserDTO() {}

    public UserDTO(Long id, String name, String email, String accountNumber, String phoneNumber, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.accountNumber = accountNumber;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    // Getters and Setters
    // ID
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    // Name
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    // Email
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    // Account Number
    public String getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    // Phone Number
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // Address
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
