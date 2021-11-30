package com.softtech.app.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by N.Bimeri on 23/08/2021.
 */
@Data
@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException(String message){
        super(message);
    }
}
