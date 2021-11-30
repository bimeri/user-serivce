package com.softtech.app.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by N.Bimeri on 25/08/2021.
 */
@Data
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class Exception extends RuntimeException {
    public Exception(String message){
        super(message);
    }
}
