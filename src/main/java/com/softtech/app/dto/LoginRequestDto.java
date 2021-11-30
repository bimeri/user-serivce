package com.softtech.app.dto;

import lombok.Data;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by N.Bimeri on 31/08/2021.
 */
@Data
public class LoginRequestDto {
    @NotBlank(message = "email is required.")
    @Size(min = 6, message = "User name must be greater than 6 character.")
    @UniqueElements
    private String userName;

    @NotBlank(message = "password is required.")
    @Size(max = 255, min = 2, message = "valid password required")
    private String password;
}
