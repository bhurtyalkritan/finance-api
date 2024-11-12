// src/main/java/com/example/portfolio_api/exception/ErrorDetails.java

package com.example.portfolio_api.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    private LocalDateTime timestamp;
    private String message;
    private String details;

    // No-args constructor
    public ErrorDetails() {}

    // All-args constructor
    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // Getters and Setters
    // Timestamp
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // Message
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Details
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}

