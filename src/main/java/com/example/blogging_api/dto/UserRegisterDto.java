package com.example.blogging_api.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserRegisterDto {

    @NotEmpty(message = "Username is required")
    @Size(min = 5, max = 15, message = "username must be 5 characters long")
    private String username;

    @NotEmpty(message = "Password is required")
    @Size(min = 8, message = "Password must be 8 characters long")
    private String password;

    @NotEmpty(message = "Email is required")
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
