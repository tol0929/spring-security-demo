package com.example.springsecuritydemo.mbgenerate.entity;

import lombok.Data;

@Data
public class LoginUserAndRoleNameEntity {
    //    private Integer id;
    private String name;
    private String email;
    private String password;
    private String roleName;
}
