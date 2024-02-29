package com.authservice.authservice.request;


import com.authservice.authservice.enumPackage.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotBlank(message = "Firstname is required")
    @Size(min = 3, max = 20, message = "Firstname must be between 3 and 20 characters")
    private String firstname;

    @NotBlank(message = "Lastname is required")
    @Size(min = 3, max = 20, message = "Lastname must be between 3 and 20 characters")
    private String lastname;

    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "password is required")
    @Size(min = 8, message = "Firstname must be more then 3 characters")
    private String password;

    private Role role;
}