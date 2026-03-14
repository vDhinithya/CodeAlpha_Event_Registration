package com.event_management;

import com.event_management.enums.RegistrationStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "registrations")
public class Registration {
    @Id
    private String id;

    @Indexed 
    @NotBlank(message = "Event ID is required")
    private String eventId;

    @NotBlank(message = "User email is required")
    @Email(message = "Invalid email format")
    private String userEmail;

    private LocalDateTime registrationDate = LocalDateTime.now();

    // Using the Enum here!
    private RegistrationStatus status = RegistrationStatus.REGISTERED; 
}