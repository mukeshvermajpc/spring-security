package com.spring.service;

import com.spring.dto.LoginDto;

public interface AuthService {
    String login(LoginDto loginDto);
}
