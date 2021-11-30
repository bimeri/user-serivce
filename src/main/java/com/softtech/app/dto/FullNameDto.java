package com.softtech.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

/**
 * Created by N.Bimeri on 20/08/2021.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FullNameDto {
    @Size(min = 2, message = "first name should be at least two character")
    private String firstName;
    private String middleName;
    private String lastName;
}
