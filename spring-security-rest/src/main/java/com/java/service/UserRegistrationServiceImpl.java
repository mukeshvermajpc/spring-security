package com.java.service;

import com.java.dto.SignUpDto;
import com.java.entity.Role;
import com.java.entity.User;
import com.java.repository.RoleRepository;
import com.java.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;

@Service
@Slf4j
public class UserRegistrationServiceImpl implements UserRegistrationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public boolean isUserAlreadyExist(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean isEmailAlreadyExist(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public String saveUser(SignUpDto signUpDto) {
        log.info("username {}", signUpDto.getUsername());
        log.info("email id {} ", signUpDto.getEmail());
        log.info("name is: {}", signUpDto.getName());
        if (isUserAlreadyExist(signUpDto.getUsername())) {
            return "Username is already registered !!";
        } else if (isEmailAlreadyExist(signUpDto.getEmail())) {
            return "Email id is already present, please logged in it, Thank you !!";
        } else {
            User user = getUser(signUpDto);
            User savedUser = userRepository.save(user);
            if (!ObjectUtils.isEmpty(savedUser)) {
                return "Registration is successful, Thank you !!";
            } else {
                return "Registration is fail, please connect with support team, Thank you !!";
            }
        }
    }

    private User getUser(SignUpDto signUpDto) {
        User user = new User();
        user.setName(signUpDto.getName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(signUpDto.getPassword());
        user.setUsername(signUpDto.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(signUpDto.getPassword()));
        user.setRoles(Collections.singleton(getRole("ROLE_ADMIN")));
        return user;
    }

    private Role getRole(String roleName) {
        Role role = roleRepository.findByName(roleName).get();
        return role;
    }
}
