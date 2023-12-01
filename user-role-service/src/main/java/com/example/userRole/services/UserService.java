package com.example.userRole.services;

import com.example.userRole.models.UserEntity;
import com.example.userRole.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    private UserRepo userRepo;


    public UserService(UserRepo userRepo
    ) {

        this.userRepo = userRepo;
    }

    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public UserEntity getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public UserEntity saveUser(UserEntity user) {
        return userRepo.save(user);
    }

    public UserEntity getUserById(int id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }


}
