package com.sachinda.urlshortingservice.exception;

import com.sachinda.urlshortingservice.dto.ApiErrorResponse;
import com.sachinda.urlshortingservice.exception.custom.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(InvalidDataException.class)
//    public ResponseEntity<ApiErrorResponse> handlerInvalidDataException(InvalidDataException invalidDataException){
//        return  buildErrorResponse(HttpStatus.BAD_REQUEST,invalidDataException.getMessage());
//    }

    @ExceptionHandler(UrlNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerUrlNotFoundException(UrlNotFoundException urlNotFoundException){
        return  buildErrorResponse(HttpStatus.NOT_FOUND,urlNotFoundException.getMessage());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handlerUserNotFoundException(UserNotFoundException userNotFoundException){
        return  buildErrorResponse(HttpStatus.NOT_FOUND,userNotFoundException.getMessage());
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<ApiErrorResponse> handlerInvalidDataException(ResourceNotFoundException resourceNotFoundException){
        return  buildErrorResponse(HttpStatus.NOT_FOUND,resourceNotFoundException.getMessage());
    }
    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<ApiErrorResponse> handlerDatabaseException(DatabaseException databaseException){
        return  buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,databaseException.getMessage());
    }

    private ResponseEntity<ApiErrorResponse> buildErrorResponse(HttpStatus status, String message) {
        ApiErrorResponse response = new ApiErrorResponse(status, message);
        return new ResponseEntity<>(response, status);
    }
}
