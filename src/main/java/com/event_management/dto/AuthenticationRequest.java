package com.event_management.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}