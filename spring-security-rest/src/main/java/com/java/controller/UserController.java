package com.java.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(("/users"))
@Slf4j
public class UserController {
    @GetMapping("/")
    public ResponseEntity<String> getUser(Authentication authentication){
        log.info(authentication.getName());
        log.info("Authorities is: {}", authentication.getAuthorities());
        log.info("Authentication details are:  {}", authentication.getDetails());
        return ResponseEntity.ok("Welcome in spring boot application");
    }
}
