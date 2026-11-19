package com.example.java.usermanagement.controller;

import com.example.java.usermanagement.dto.UserDto;
import com.example.java.usermanagement.entity.User;
import com.example.java.usermanagement.exception.ErrorDetails;
import com.example.java.usermanagement.exception.ResourceNotFoundException;
import com.example.java.usermanagement.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

/*
controller handles all the incoming traffic and calls service layer which then calls repository layer.
 */

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    /*
    PostMapping because it's a post endpoint.
    ResponseEntity to customize the response.
    RequestBody to convert the incoming JSON to Java object
    returning the ResponseEntity with the newly createdUser and created status code 201
     */

    @PostMapping("v2/create-user")
    public ResponseEntity<UserDto> createUser(@Valid  @RequestBody UserDto user){
        UserDto savedUser = userService.createUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    /*
    with new dto, return type is userDto where user Id is passed to service layer. and returned user dto
     */
    @GetMapping("/v2/get-user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable("id") Long userId){
        UserDto retrievedUser = userService.getUserById(userId);
        return new ResponseEntity<>(retrievedUser, HttpStatus.OK);
    }

    @GetMapping("v2/get-all-users")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> allUserList = userService.getAllUsers();
        return new ResponseEntity<>(allUserList, HttpStatus.OK);
     }

    @PutMapping("v2/update-user/{id}")
    public ResponseEntity<UserDto> updateUser(@Valid @PathVariable("id") Long userId , @RequestBody User user){
        user.setId(userId);
        UserDto updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("v2/delete-user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        userService.DeleteUser(userId);
        return new ResponseEntity<>("User Deleted",HttpStatus.OK);
    }


//    @ExceptionHandler(ResourceNotFoundException.class)
//    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
//                                                                        WebRequest webRequest){
//
//        ErrorDetails errorDetails = new ErrorDetails(
//                LocalDateTime.now(),
//                exception.getMessage(),
//                webRequest.getDescription(true),
//                "USER_NOT_FOUND"
//        );
//        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//    }
}
