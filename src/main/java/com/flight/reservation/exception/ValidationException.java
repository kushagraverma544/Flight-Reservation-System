package com.flight.reservation.exception;

public class ValidationException extends RuntimeException {

  private String fieldName;
  private String errorCode;

  public ValidationException(String message) {
    super(message);
  }

  public ValidationException(String fieldName, String message, String errorCode) {
    super(message);
    this.fieldName = fieldName;
    this.errorCode = errorCode;
  }

  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }

  public String getFieldName() {
    return fieldName;
  }

  public String getErrorCode() {
    return errorCode;
  }
}
