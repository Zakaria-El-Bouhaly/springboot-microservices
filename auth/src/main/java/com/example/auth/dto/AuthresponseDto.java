package com.example.auth.dto;

import com.example.auth.models.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthresponseDto {
    private String token;
    private UserEntity user;

}
