package com.example.userRole.dto;


import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String password;
    private String fullName;
    private boolean active;

    private String role;
}
