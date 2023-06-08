package com.example.springmvcapp.service.impl;

import com.example.springmvcapp.dto.RegestrationDto;
import com.example.springmvcapp.models.Role;
import com.example.springmvcapp.models.UserEntity;
import com.example.springmvcapp.repository.RoleRepository;
import com.example.springmvcapp.repository.UserRepository;
import com.example.springmvcapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(RegestrationDto regestrationDto) {
        UserEntity user = new UserEntity();
        user.setUsername(regestrationDto.getUsername());
        user.setEmail(regestrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(regestrationDto.getPassword()));
        Role role = roleRepository.findByName("USER");
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
