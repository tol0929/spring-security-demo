//package com.example.springsecuritydemo.controller;
//
//import com.example.springsecuritydemo.service.SampleService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//@Slf4j
//@RequiredArgsConstructor
//public class SampleController {
//    private final SampleService sampleService;
//
//    @GetMapping("/sample")
//
//    public String sample() {
//        sampleService.testMybatis3DynamicSql();
//        log.info("encoded:{}", sampleService.encodeBCrypt("admin"));
//        return "sample";
//    }
//}
