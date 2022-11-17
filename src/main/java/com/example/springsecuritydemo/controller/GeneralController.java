package com.example.springsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/general")
public class GeneralController {
    @GetMapping
    public String general() {
        return "general";
    }
}
