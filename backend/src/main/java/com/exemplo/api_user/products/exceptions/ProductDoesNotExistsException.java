package com.exemplo.api_user.products.exceptions;

public class ProductDoesNotExistsException extends Exception {

    public ProductDoesNotExistsException(String message) {
        super(message);
    }
}
