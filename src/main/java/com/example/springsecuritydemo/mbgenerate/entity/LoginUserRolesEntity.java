package com.example.springsecuritydemo.mbgenerate.entity;

import lombok.Getter;

import java.util.List;

@Getter
public class LoginUserRolesEntity {
    private LoginUser loginUser;
    private List<Roles> roles;
}
