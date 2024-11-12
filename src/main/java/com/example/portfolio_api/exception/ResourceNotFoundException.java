// src/main/java/com/example/portfolio_api/exception/ResourceNotFoundException.java

package com.example.portfolio_api.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
