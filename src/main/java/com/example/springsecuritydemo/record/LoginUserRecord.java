package com.example.springsecuritydemo.record;

import java.util.List;

public record LoginUserRecord(String email, String name, String password, List<String> roleList) {
}
