package com.example.userRole.controllers;

import com.example.userRole.dto.CustomErrorResponse;
import com.example.userRole.services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
public class RolesController {


    private final RoleService roleService;

    public RolesController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("")
    public ResponseEntity getRoles() {
        try {
            return ResponseEntity.ok(roleService.getAllUsers());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("Roles could not be retrieved"));
        }
    }

    @GetMapping("name/{name}")
    public ResponseEntity getRoleByName(@PathVariable String name) {
        try {
            return ResponseEntity.ok(roleService.getRoleByName(name));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new CustomErrorResponse("Role could not be retrieved"));
        }
    }

}
