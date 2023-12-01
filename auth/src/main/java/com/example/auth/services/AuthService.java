package com.example.auth.services;

import com.example.auth.config.JwtUtils;
import com.example.auth.dto.AuthresponseDto;
import com.example.auth.models.Role;
import com.example.auth.models.UserEntity;
import com.example.auth.openfeignclients.UserRoleFeignClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    private final UserRoleFeignClient userRoleFeignClient;

    private final ObjectMapper objectMapper;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtils tokenGenerator;


    public AuthService(
            UserRoleFeignClient userRoleFeignClient,
            PasswordEncoder passwordEncoder,
            JwtUtils tokenGenerator,
            ObjectMapper objectMapper
    ) {
        this.userRoleFeignClient = userRoleFeignClient;
        this.passwordEncoder = passwordEncoder;
        this.tokenGenerator = tokenGenerator;
        this.objectMapper = objectMapper;
    }

    public UserEntity register(String email, String password) {

        try {
            ResponseEntity<?> userByEmail = userRoleFeignClient.getUserByEmail(email);
            if (userByEmail.getStatusCode().is2xxSuccessful()) {
                throw new RuntimeException("User already exists");
            }
            return null;
        } catch (Exception e) {
            UserEntity user = new UserEntity();
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode(password));
            List roles = new ArrayList();

            ResponseEntity re = userRoleFeignClient.getRoleByName("USER");
            Role role = objectMapper.convertValue(re.getBody(), Role.class);
            roles.add(role);
            user.setRoles(roles);
            userRoleFeignClient.save(user);
            return user;
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
