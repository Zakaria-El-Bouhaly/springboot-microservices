package com.example.userRole.services;

import com.example.userRole.dto.UserDto;
import com.example.userRole.models.Role;
import com.example.userRole.models.UserEntity;
import com.example.userRole.repository.RoleRepo;
import com.example.userRole.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {


    private final UserRepo userRepo;
    private final RoleRepo roleRepo;


    public UserService(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }


    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    public UserEntity getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public UserEntity saveUser(UserDto userDto) {
        UserEntity user = new UserEntity();
        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setPassword(userDto.getPassword());
        List<Role> roles = new ArrayList<>();
        Role role = roleRepo.findByName(userDto.getRole()).get();
        roles.add(role);
        user.setRoles(roles);
        return userRepo.save(user);
    }

    public UserEntity getUserById(int id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }


}
