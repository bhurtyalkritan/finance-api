// AuthenticationResponse.java
package com.example.portfolio_api.dto;

public class AuthenticationResponse {
    private String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    // Getter
    public String getToken() {
        return token;
    }
}