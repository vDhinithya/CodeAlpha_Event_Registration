package com.event_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegistrationRequestDTO {

    @NotBlank(message = "Event ID is required")
    private String eventId;

    @NotBlank(message = "User email is required")
    @Email(message = "Invalid email format")
    private String userEmail;
}