package com.event_management.service;

import com.event_management.entity.Event;
import com.event_management.entity.Registration;
import com.event_management.enums.RegistrationStatus;
import com.event_management.exception.DuplicateRegistrationException;
import com.event_management.exception.EventFullException;
import com.event_management.exception.ResourceNotFoundException;
import com.event_management.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final EventService eventService;

    @Transactional
    public Registration registerUserForEvent(String eventId, String userEmail) {
        Event event = eventService.getEventById(eventId);

        if (registrationRepository.existsByUserEmailAndEventId(userEmail, eventId)) {
            throw new DuplicateRegistrationException("User " + userEmail + " is already registered for this event.");
        }

        if (event.getCurrentRegistrations() >= event.getMaxCapacity()) {
            throw new EventFullException("Event is already at full capacity.");
        }

        Registration registration = new Registration();
        registration.setEventId(eventId);
        registration.setUserEmail(userEmail);
        registration.setStatus(RegistrationStatus.REGISTERED);
        Registration savedRegistration = registrationRepository.save(registration);

        event.setCurrentRegistrations(event.getCurrentRegistrations() + 1);
        eventService.updateEvent(event);

        return savedRegistration;
    }

    public List<Registration> getUserRegistrations(String userEmail) {
        return registrationRepository.findByUserEmail(userEmail);
    }

    @Transactional
    public void cancelRegistration(String registrationId) {
        Registration registration = registrationRepository.findById(registrationId)
                .orElseThrow(() -> new ResourceNotFoundException("Registration not found with ID: " + registrationId));

        if (registration.getStatus() == RegistrationStatus.CANCELLED) {
            return;
        }

        registration.setStatus(RegistrationStatus.CANCELLED);
        registrationRepository.save(registration);

        Event event = eventService.getEventById(registration.getEventId());
        event.setCurrentRegistrations(event.getCurrentRegistrations() - 1);
        eventService.updateEvent(event);
    }
}