package com.example.springsecuritydemo.record;

import java.util.List;

public record LoginUserDto(String email, String name, String password, List<String> roleList) {
}
