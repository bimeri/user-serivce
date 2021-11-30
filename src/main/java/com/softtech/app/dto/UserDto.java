package com.softtech.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * Created by N.Bimeri on 20/08/2021.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private FullNameDto fullNameDto;
    private String userName;
    private String email;
    private String password;
    private int age;
    private Date dateOfBirth;
}
