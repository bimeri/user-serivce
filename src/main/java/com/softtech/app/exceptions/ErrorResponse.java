package com.softtech.app.exceptions;

import lombok.Data;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * Created by N.Bimeri on 23/08/2021.
 */
@Data
@ToString
public class ErrorResponse {
    private String errorCode;
    private String errorMessage;
    private String url;
    private Date timestamp;
    private HttpStatus httpStatus;
}
