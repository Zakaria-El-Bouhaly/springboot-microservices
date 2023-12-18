package com.example.auth.services;

import com.example.auth.config.JwtUtils;
import com.example.auth.dto.AuthresponseDto;
import com.example.auth.dto.RegisterDto;
import com.example.auth.dto.UserDto;
import com.example.auth.models.UserEntity;
import com.example.auth.openfeignclients.UserRoleFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRoleFeignClient userRoleFeignClient;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils tokenGenerator;


    public AuthService(UserRoleFeignClient userRoleFeignClient,
                       PasswordEncoder passwordEncoder,
                       JwtUtils tokenGenerator,
                       ObjectMapper objectMapper) {
        this.userRoleFeignClient = userRoleFeignClient;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
        this.objectMapper = objectMapper;
    }

    public UserEntity register(RegisterDto registerDto) {

        try {
            ResponseEntity<?> userByEmail = userRoleFeignClient.getUserByEmail(registerDto.getEmail());
            if (userByEmail.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("User already exists");
            }
            return null;
        } catch (FeignException.NotFound e) {
            UserDto user = new UserDto();
            user.setEmail(registerDto.getEmail());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            user.setFullName(registerDto.getFullName());
            user.setRole("USER");

            try {
                ResponseEntity<?> userResponse = userRoleFeignClient.save(user);
                UserEntity userEntity = objectMapper.convertValue(userResponse.getBody(), UserEntity.class);
                return userEntity;
            } catch (FeignException ex) {
                throw new RuntimeException("Error saving user");
            }
        }
    }

    public AuthresponseDto login(String email, String password) {
        // find user by email
        ResponseEntity<?> userByEmail = userRoleFeignClient.getUserByEmail(email);
        if (!userByEmail.getStatusCode().is2xxSuccessful()) {
            throw new RuntimeException("User not found");
        }

        UserEntity user = objectMapper.convertValue(userByEmail.getBody(), UserEntity.class);

        // check if password is correct
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("incorrect password");
        }

        // generate token
        String token = tokenGenerator.generateToken(user);

        // return token and user
        return new AuthresponseDto(token, user);
    }

}
