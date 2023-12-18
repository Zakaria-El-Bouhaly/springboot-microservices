package com.example.auth.controllers;

import com.example.auth.dto.AuthresponseDto;
import com.example.auth.dto.CustomErrorResponse;
import com.example.auth.dto.LoginDto;
import com.example.auth.dto.RegisterDto;
import com.example.auth.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto registerDto) {
        try {
            authService.register(registerDto);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomErrorResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto) {
        try {
            AuthresponseDto authresponseDto = authService.login(loginDto.getEmail(), loginDto.getPassword());
            return ResponseEntity.ok(authresponseDto);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomErrorResponse("Invalid credentials"), HttpStatus.BAD_REQUEST);
        }
    }

}
