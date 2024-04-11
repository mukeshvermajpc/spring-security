package com.java.service;

import com.java.dto.SignUpDto;

public interface UserRegistrationService {
    boolean isUserAlreadyExist(String username);

    boolean isEmailAlreadyExist(String email);

    String saveUser(SignUpDto signUpDto);
}
