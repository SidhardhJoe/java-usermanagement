package com.example.java.usermanagement.mapper;

import com.example.java.usermanagement.dto.UserDto;
import com.example.java.usermanagement.entity.User;

public class UserMapper {

    // convert user into UserDto
    public static UserDto mapToUerDto(User user){

        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }

    // convert userDto to User
    public static User mapToUser(UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getEmail()
        );
    }
}
