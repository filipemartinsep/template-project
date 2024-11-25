package com.exemplo.api_user.users.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityDoesNotExistsException.class)
    public ResponseEntity<?> handlerEntityDoesNotExistException(EntityDoesNotExistsException exception, WebRequest request) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<?> handlerEntityAlreadyExistsException(EntityAlreadyExistsException exception, WebRequest request) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
