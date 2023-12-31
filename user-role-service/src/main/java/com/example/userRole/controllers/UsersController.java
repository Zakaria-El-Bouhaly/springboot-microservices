package com.example.userRole.controllers;

import com.example.userRole.dto.CustomErrorResponse;
import com.example.userRole.dto.UserDto;
import com.example.userRole.models.UserEntity;
import com.example.userRole.services.UserService;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {


    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity getUsers() {
        try {
            List<UserEntity> users = userService.getAllUsers();
            users.forEach(user -> user.setPassword(null));
            return ResponseEntity.ok(users);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("Users could not be retrieved"));
        }
    }

    @GetMapping("email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {

        UserEntity user = userService.getUserByEmail(email);
        if (user == null) {
            return new ResponseEntity<>(new CustomErrorResponse("User could not be retrieved"), HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(user);

    }


    @GetMapping("id/{id}")
    public ResponseEntity getUserById(@PathVariable int id) {

        UserEntity user = userService.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(new CustomErrorResponse("User could not be retrieved"), HttpStatus.NOT_FOUND);
        }
        user.setPassword(null);
        return ResponseEntity.ok(user);

    }

    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserDto userDto) {
        try {
            UserEntity savedUser = userService.saveUser(userDto);
            savedUser.setPassword(null);
            return ResponseEntity.ok(savedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("User could not be saved"));
        }
    }


}
