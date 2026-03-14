package com.event_management.repository;

import com.event_management.entity.Registration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegistrationRepository extends MongoRepository<Registration, String> {

    List<Registration> findByUserEmail(String userEmail);
    
    boolean existsByUserEmailAndEventId(String userEmail, String eventId);
}