package com.softtech.app.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by N.Bimeri on 24/08/2021.
 */
@Data
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MethodNotFoundException extends RuntimeException {
    public MethodNotFoundException(String message){
        super(message);
    }
}
