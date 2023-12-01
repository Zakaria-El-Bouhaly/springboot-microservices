package com.example.theproject.openfeignclients;

import com.example.theproject.models.Role;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Component
@FeignClient(name = "user-role-service")

public interface RoleFeignClient {
    @GetMapping("api/roles")
    Role getRoleByName(@RequestParam String name);
}
