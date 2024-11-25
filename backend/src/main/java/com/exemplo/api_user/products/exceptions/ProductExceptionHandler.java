package com.exemplo.api_user.products.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ProductAlreadyExistsException.class)
  public ResponseEntity<?> handlerProductAlreadyExistsException(ProductAlreadyExistsException exception, WebRequest request) {

    return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
  }

  @ExceptionHandler(SkuAlreadyExistsException.class)
  public ResponseEntity<?> handlerSkuAlreadyExistsException(SkuAlreadyExistsException exception, WebRequest request) {

    return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
  }
}
