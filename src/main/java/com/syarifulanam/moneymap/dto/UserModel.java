package com.syarifulanam.moneymap.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserModel {

    @NotBlank(message = "Please enter name")
    private String name;

    @Size(min = 10, message = "Email should be at least 10 characters")
    @NotNull(message = "Please enter email")
    @Email(message = "Please enter valid email format")
    private String email;

    @NotNull(message = "Please enter password")
    @Size(min = 5, message = "Password should be at least 5 characters")
    private String password;

    private Long age = 0L;
}
