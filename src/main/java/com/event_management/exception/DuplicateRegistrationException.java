package com.event_management.exception;

public class DuplicateRegistrationException extends RuntimeException {
  public DuplicateRegistrationException(String message) {
    super(message);
  }
}