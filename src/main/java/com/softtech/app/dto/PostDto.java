package com.softtech.app.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.softtech.app.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by N.Bimeri on 27/08/2021.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private String message;

    @JsonIgnore
    private User user;
}
