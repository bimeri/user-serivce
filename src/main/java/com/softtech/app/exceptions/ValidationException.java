package com.softtech.app.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by N.Bimeri on 27/08/2021.
 */

@Data
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ValidationException extends RuntimeException {
    public ValidationException(String message){
        super(message);
    }
}
