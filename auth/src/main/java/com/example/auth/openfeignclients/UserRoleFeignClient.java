package com.example.auth.openfeignclients;

import com.example.auth.models.Role;
import com.example.auth.models.UserEntity;
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
}
