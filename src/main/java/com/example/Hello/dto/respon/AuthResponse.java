package com.example.Hello.dto.respon;

import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;

    // Constructor có tham số
    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }
}
