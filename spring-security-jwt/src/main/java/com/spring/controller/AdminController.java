package com.spring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdminController {
    @GetMapping("/admin")
    public ResponseEntity<String> helloAdmin() {
        return ResponseEntity.ok("Hello Admin");
    }

    @GetMapping("/user")
    public ResponseEntity<String> helloUser() {
        return ResponseEntity.ok("Hello User");
    }

    @GetMapping("/welcome/{name}")
    public ResponseEntity<String> welcome(@PathVariable String name) {
        return ResponseEntity.ok("Welcome " + name + " in spring boot application");
    }
}
