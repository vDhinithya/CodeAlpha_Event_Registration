package com.event_management.dto;

import com.event_management.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {
    private String name;
    private String email;
    private String password;
    private Role role; // We'll pass ADMIN, REGISTRAR, or USER here
}