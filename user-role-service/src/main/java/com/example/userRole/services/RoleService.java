package com.example.userRole.services;

import com.example.userRole.models.Role;
import com.example.userRole.repository.RoleRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {


    private final RoleRepo roleRepo;


    public RoleService(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    public List<Role> getAllUsers() {
        return roleRepo.findAll();
    }

    public Role getRoleByName(String name) {
        return roleRepo.findByName(name).orElseThrow(() -> new RuntimeException("Role not found"));
    }


}
