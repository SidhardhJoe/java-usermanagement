package com.example.java.usermanagement.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // specifies this class as an Entity
@Table(name="users") // creates table with name users. More options to customize

/*

Entity refers to a Table in a DB
Lombok allows prevention of boilerplate code.
@Getter - creates a getter method for all the variables in the class getId(id){return this.id}
@Setter - creates a setter method for all the variables in the class setId(id){this.id = id}
@NoArgsConstructor - creates a constructor with no arguments public Student() { }
@AllArgsConstructor - creates a constructor with all arguments public Student(int id, String firstName, String lastName) { set values}


all the annotations from the variables are from jakarta persistence which allows customization of column in the table
*/

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

}
