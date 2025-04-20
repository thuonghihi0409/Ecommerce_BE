package com.example.Hello.dto.request;


import com.example.Hello.entity.Role;
import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
    private String email;
    private Role role;
}

