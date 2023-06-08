package com.example.springmvcapp.service;

import com.example.springmvcapp.dto.RegestrationDto;
import com.example.springmvcapp.models.UserEntity;

public interface UserService {
    void saveUser(RegestrationDto regestrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}
