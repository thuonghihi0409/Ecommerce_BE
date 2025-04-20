package com.example.Hello.dto;

import com.example.Hello.entity.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long userId;
    private String username;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String address;
    private Role role;
    private boolean isActive;
}

