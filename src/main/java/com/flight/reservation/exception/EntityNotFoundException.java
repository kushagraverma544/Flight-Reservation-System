package com.flight.reservation.exception;

public class EntityNotFoundException extends RuntimeException {

  private String entityName;
  private String identifier;

  public EntityNotFoundException(String message) {
    super(message);
  }

  public EntityNotFoundException(String entityName, String identifier) {
    super(String.format("%s not found with identifier: %s", entityName, identifier));
    this.entityName = entityName;
    this.identifier = identifier;
  }

  public EntityNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public String getEntityName() {
    return entityName;
  }

  public String getIdentifier() {
    return identifier;
  }
}
