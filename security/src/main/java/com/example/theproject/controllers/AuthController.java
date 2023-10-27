package com.example.theproject.controllers;

import com.example.theproject.config.TokenGenerator;
import com.example.theproject.dto.AuthresponseDto;
import com.example.theproject.dto.CustomErrorResponse;
import com.example.theproject.dto.LoginDto;
import com.example.theproject.dto.RegisterDto;
import com.example.theproject.models.Role;
import com.example.theproject.models.UserEntity;
import com.example.theproject.repository.RoleRepo;
import com.example.theproject.repository.UserRepo;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepo userRepo;
    private RoleRepo roleRepo;
    private PasswordEncoder passwordEncoder;
    private TokenGenerator tokenGenerator;


    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder, TokenGenerator tokenGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping("register")
    @Transactional
    public ResponseEntity<String> register(@RequestBody @Valid RegisterDto registerDto) {
        if (userRepo.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("username already taken", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = new UserEntity();
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        List roles = new ArrayList();
        Role role = roleRepo.findByName("USER").get();
        roles.add(role);
        user.setRoles(roles);
        userRepo.save(user);
        return new ResponseEntity<>("user created", HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDto loginDto) {

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    ));

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String token = tokenGenerator.generateToken(userDetails);
            UserEntity user = userRepo.findByEmail(loginDto.getEmail()).get();
            AuthresponseDto authresponseDto = new AuthresponseDto(token, user);
            return ResponseEntity.ok(authresponseDto);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomErrorResponse("invalid credentials"), HttpStatus.UNAUTHORIZED);
        }
    }
}
