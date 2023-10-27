package com.example.theproject.dto;

import com.example.theproject.models.UserEntity;
import lombok.Data;

@Data
@lombok.AllArgsConstructor
public class AuthresponseDto {
    private String token;
    private UserEntity user;

}
