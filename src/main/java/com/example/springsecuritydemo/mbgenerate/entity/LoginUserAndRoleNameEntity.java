package com.example.springsecuritydemo.mbgenerate.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginUserAndRoleNameEntity {
    //    private Integer id;
    private String name;
    private String email;
    private String password;
    private String roleName;
}
