package com.example.auth.openfeignclients;

import com.example.auth.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@Component
@FeignClient(name = "user-role-service")
public interface UserRoleFeignClient {
    @GetMapping("api/users/email/{email}")
    ResponseEntity<?> getUserByEmail(@PathVariable String email);

    @PostMapping(value = "api/users")
    ResponseEntity<?> save(@RequestBody UserDto userDto);


    @GetMapping("api/roles/name/{name}")
    ResponseEntity<?> getRoleByName(@PathVariable String name);
}
