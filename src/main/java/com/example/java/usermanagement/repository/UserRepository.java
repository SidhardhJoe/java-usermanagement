package com.example.java.usermanagement.repository;

import com.example.java.usermanagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
The entity is defined which acts as a sort of like a schema, where the type and characteristics are defined

Repository extends JpaRepository where the Entity and the type of the primary key
 */

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> getUserByEmail(String email);

}
