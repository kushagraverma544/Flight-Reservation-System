package com.flight.reservation.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlightReservationException extends RuntimeException {

  private String errorCode;
  private int statusCode;

  public FlightReservationException(String message) {
    super(message);
    this.statusCode = 500;
  }

  public FlightReservationException(String message, String errorCode, int statusCode) {
    super(message);
    this.errorCode = errorCode;
    this.statusCode = statusCode;
  }

  public FlightReservationException(String message, Throwable cause) {
    super(message, cause);
    this.statusCode = 500;
  }

  public FlightReservationException(String message, String errorCode, int statusCode, Throwable cause) {
    super(message, cause);
    this.errorCode = errorCode;
    this.statusCode = statusCode;
  }
}
