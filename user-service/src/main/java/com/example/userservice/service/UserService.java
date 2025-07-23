package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User save(User user);
    UserDto findById(Long id);
}
