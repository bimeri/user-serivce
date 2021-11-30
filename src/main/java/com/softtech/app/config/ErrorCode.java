package com.softtech.app.config;

/**
 * Created by N.Bimeri on 23/08/2021.
 */
public enum ErrorCode {
    USER_NOT_FOUND("User does not exist"),
    ACCESS_DENIED("user not authorized to perform this request"),
    BAD_REQUEST("you sent bad request"),
    METHOD_NOT_ALLOWED("method not allowed"),
    UNPROCESSED_ENTITY("method not processed"),
    NOT_FOUND("method or url not found"),
    INTERNAL_SERVER_ERROR("There was an internal server error"),
    VALIDATION_ERROR("The value passed is in valid"),
    EMAIL_EXIST("Email already exists");

    private String message;

     ErrorCode(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
