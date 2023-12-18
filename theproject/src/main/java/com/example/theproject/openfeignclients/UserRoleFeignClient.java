package com.example.theproject.openfeignclients;


import com.example.theproject.models.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@Component
@FeignClient(name = "user-role-service")
public interface UserRoleFeignClient {
    @GetMapping("api/users/email/{email}")
    ResponseEntity<?> getUserByEmail(@PathVariable String email);

    @PostMapping("api/users")
    ResponseEntity<?> save(@RequestBody UserEntity user);

    @GetMapping("api/roles/name/{name}")
    ResponseEntity<?> getRoleByName(@PathVariable String name);

    @GetMapping("api/users/{id}")
    ResponseEntity<?> getUserById(@PathVariable int id);
}
