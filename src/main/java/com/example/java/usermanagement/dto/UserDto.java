package com.example.java.usermanagement.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        description = "Description for UserDto model information"
)
public class UserDto {
    private Long id;

    @Schema( description = "User's first name")
    @NotEmpty(message = "User first name should not be null or empty")
    private String firstName;

    @Schema(description = "User's last name")
    @NotEmpty(message = "User last name should not be null or empty")
    private String lastName;

    @Schema(description = "User's emaill address")
    @NotEmpty(message = "User Email should not be null or empty")
    @Email
    private String email;
}
