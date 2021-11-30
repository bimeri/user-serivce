package com.softtech.app.exceptions;

import com.softtech.app.config.ErrorCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.softtech.app.config.ErrorCode.*;

/**
 * Created by N.Bimeri on 23/08/2021.
 */

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {EmailExistsException.class}) // 409
    public ResponseEntity<ErrorResponse> handleEmailExistException(EmailExistsException ex, HttpServletRequest httpServletRequest){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorCode.EMAIL_EXIST.toString());
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setUrl(httpServletRequest.getRequestURI());
        errorResponse.setTimestamp(new Date());
        errorResponse.setHttpStatus(HttpStatus.CONFLICT);
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {UserNotFoundException.class}) // 400
    public ResponseEntity<ErrorResponse> handleUserNotFountException(UserNotFoundException ex, HttpServletRequest httpServletRequest){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(ErrorCode.USER_NOT_FOUND.toString());
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setUrl(httpServletRequest.getRequestURI());
        errorResponse.setTimestamp(new Date());
        errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AccessDeniedException.class}) //401
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException ex, HttpServletRequest request){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(METHOD_NOT_ALLOWED.toString());
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setUrl(request.getRequestURI());
        errorResponse.setTimestamp(new Date());
        errorResponse.setHttpStatus(HttpStatus.UNAUTHORIZED);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {MethodNotFoundException.class}) // 400
    public ResponseEntity<ErrorResponse> handleMethodNotFoundException(MethodNotFoundException ex, HttpServletRequest httpServletRequest){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(NOT_FOUND.toString());
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setTimestamp(new Date());
        errorResponse.setHttpStatus(HttpStatus.NOT_FOUND);
        errorResponse.setUrl(httpServletRequest.getRequestURI());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {Exception.class}) // 500
    public ResponseEntity<ErrorResponse> handleAllException(Exception ex, HttpServletRequest httpServletRequest){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(INTERNAL_SERVER_ERROR.toString());
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setUrl(httpServletRequest.getRequestURI());
        errorResponse.setTimestamp(new Date());
        errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ValidationException.class}) // 500
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException ex, HttpServletRequest httpServletRequest){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(VALIDATION_ERROR.toString());
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setUrl(httpServletRequest.getRequestURI());
        errorResponse.setTimestamp(new Date());
        errorResponse.setHttpStatus(HttpStatus.NOT_ACCEPTABLE);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpServletRequest httpServletRequest) {
            List<String> errors = new ArrayList<String>();
            for (FieldError error : ex.getBindingResult().getFieldErrors()) {
                errors.add(error.getField() + ": " + error.getDefaultMessage());
            }
            for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
                errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
            }
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setErrorCode(BAD_REQUEST.toString());
            errorResponse.setErrorMessage(ex.getLocalizedMessage());
            errorResponse.setUrl(httpServletRequest.getRequestURI());
            errorResponse.setTimestamp(new Date());
            errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpServletRequest httpServletRequest) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(UNPROCESSED_ENTITY.toString());
        errorResponse.setErrorMessage(ex.getMessage());
        errorResponse.setUrl(httpServletRequest.getRequestURI());
        errorResponse.setTimestamp(new Date());
        errorResponse.setHttpStatus(HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(errorResponse, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
