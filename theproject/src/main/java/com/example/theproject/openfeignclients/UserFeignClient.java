package com.example.theproject.openfeignclients;

import com.example.theproject.models.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;


@Component
@FeignClient(name = "project")

public interface UserFeignClient {
    @GetMapping("api/users/{email}")
    UserEntity getUserByEmail(@PathVariable String email);

    @PostMapping("api/users")
    UserEntity save(@RequestBody UserEntity user);

    @GetMapping("api/users/{id}")
    UserEntity getUserById(@RequestParam int id);
}
