package com.event_management.controller;

import com.event_management.dto.RegistrationRequestDTO;
import com.event_management.entity.Registration;
import com.event_management.service.RegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events/registrations")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<Registration> registerForEvent(@Valid @RequestBody RegistrationRequestDTO requestDTO) {
        Registration registration = registrationService.registerUserForEvent(
                requestDTO.getEventId(),
                requestDTO.getUserEmail()
        );
        return new ResponseEntity<>(registration, HttpStatus.CREATED);
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<Registration>> getUserRegistrations(@PathVariable String email) {
        return ResponseEntity.ok(registrationService.getUserRegistrations(email));
    }

    @DeleteMapping("/{registrationId}")
    public ResponseEntity<String> cancelRegistration(@PathVariable String registrationId) {
        registrationService.cancelRegistration(registrationId);
        return ResponseEntity.ok("Registration cancelled successfully.");
    }
}