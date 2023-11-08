package com.example.theproject.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {

    @Email
    @NotEmpty(message = "email is required")
    private String email;

    private String phone;
    @NotBlank(message = "password is required")
    private String password;
    @NotBlank(message = "passwordConfirm is required")
    private String passwordConfirm;

    @NotBlank(message = "username is required")
    private String fullName;

}
