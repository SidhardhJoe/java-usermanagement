package com.example.java.usermanagement.service.impl;

import com.example.java.usermanagement.dto.UserDto;
import com.example.java.usermanagement.entity.User;
import com.example.java.usermanagement.exception.EmailAlreadyExistsException;
import com.example.java.usermanagement.exception.ResourceNotFoundException;
import com.example.java.usermanagement.mapper.AutoUserMapper;
import com.example.java.usermanagement.mapper.UserMapper;
import com.example.java.usermanagement.repository.UserRepository;
import com.example.java.usermanagement.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


/*
Inside impl (implementation) a service file is created, this communicates with the repository layer and sent's data to it
business logic is written in this.

This class implements the interface UserService that is created to user to type method mentioned in it.
An AllArgsConstructor is used here to create a constructor with all arguments

NoArgsConstructor will create an empty constructor with nothing inside it which can cause errors.

userRepository is called here and used .save to save the incoming method parameter to repository

.save is from CrudRepository which is extended by ListCrudRepository which is extended by JpaRepository.
this allows the incoming data to be saved to DB

Rather than using model mapper we are using AutoUserMapper

 */

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository ;
    private ModelMapper modelMapper;


    // using modelMapper instead of custom userMapper;
    @Override
    public UserDto createUser(UserDto userDto) {
//        User user = modelMapper.map(userDto, User.class);
//        return modelMapper.map(savedUser, UserDto.class);

        Optional<User> optionalUser = userRepository.getUserByEmail(userDto.getEmail());

        if(optionalUser.isPresent()){
            throw new EmailAlreadyExistsException("User already exists with this email");
        }

        User user = AutoUserMapper.MAPPER.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return AutoUserMapper.MAPPER.mapToUserDto(savedUser);
    }

    /*
    incoming id is used to find the user from repo, and it returns user type
    optionaluser.get() gets the user details and sent's it to mapper which maps User to userDto and returns it
    since controller is returning a userDto.
     */
    @Override
    public UserDto getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId)
        );
//        User user = optionalUser.get();
//        return UserMapper.mapToUerDto(user);
        return AutoUserMapper.MAPPER.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream().map((user -> modelMapper.map(user, UserDto.class))).toList();
    }

    @Override
    public UserDto updateUser(User user) {
        User existingUser = userRepository.findById(user.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", user.getId()));
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setEmail(user.getEmail());

        User savedUser =  userRepository.save(existingUser);
        return UserMapper.mapToUerDto(savedUser);

    }

    @Override
    public void DeleteUser(Long userId) {
        User existingUser = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));
        userRepository.deleteById(existingUser.getId());
    }
}
