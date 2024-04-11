package com.java.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FromController {
    @GetMapping("/")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Welcome in spring boot security application");
    }

    @GetMapping("/loggedIn")
    public ResponseEntity<String> loggedIn(Authentication authentication) {
        String username = authentication.getName();
        String authorities = authentication.getAuthorities().toString();
        User principal = (User) authentication.getPrincipal();
        String st = authentication.getDetails().toString();
        log.info("Logged in username  {}", username);
        log.info("authorities are {}", authorities);
        log.info("Principal is {}", principal);
        log.info("st is: {}", st);
        return ResponseEntity.ok("Welcome in spring security " + username);
    }
}
