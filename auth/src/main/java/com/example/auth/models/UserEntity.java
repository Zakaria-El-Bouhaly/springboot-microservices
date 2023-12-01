package com.example.auth.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class UserEntity {
    private int id;
    private String email;
    private String fullName;

    boolean isActivated;

    private String password;

    private List<Role> roles = new ArrayList<>();


}
