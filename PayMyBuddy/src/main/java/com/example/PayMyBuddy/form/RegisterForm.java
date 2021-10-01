package com.example.PayMyBuddy.form;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class RegisterForm {
    @NotNull
    @NotBlank
    String email;
    String firstName;
    String lastName;
    @NotNull
    @NotBlank
    @Size(min = 5, max = 30, message = "Invalid password. Must be between 5 and 30 characters.")
    String password;
    String confirmPassword;
}
