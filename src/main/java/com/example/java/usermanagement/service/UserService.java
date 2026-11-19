package com.example.java.usermanagement.service;

import com.example.java.usermanagement.dto.UserDto;
import com.example.java.usermanagement.entity.User;

import java.util.List;

/*
This is an interface for the service file, the type is defined in this
 */

public interface UserService {
    UserDto createUser(UserDto user);

    UserDto getUserById(Long userId);

    List<UserDto> getAllUsers();

    UserDto updateUser(User user);

    void DeleteUser(Long userId);
}
