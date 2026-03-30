package com.flight.reservation.exception;

import com.flight.reservation.constants.ErrorCode;
import com.flight.reservation.constants.MessageKeys;
import com.flight.reservation.dto.failureResponse.ErrorResponseDto;
import com.flight.reservation.service.MessageService;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private final MessageService messageService;

  public GlobalExceptionHandler(MessageService messageService) {
    this.messageService = messageService;
  }

  /**
   * Handle FlightReservationException with custom error code and status
   */
  @ExceptionHandler(FlightReservationException.class)
  public ResponseEntity<ErrorResponseDto> handleFlightReservationException(
      FlightReservationException ex,
      WebRequest request) {

    ErrorResponseDto errorResponse = ErrorResponseDto.builder()
        .message(ex.getMessage())
        .status(ex.getStatusCode())
        .errorCode(ErrorCode.FLIGHT_RESERVATION_ERROR)
        .timestamp(LocalDateTime.now())
        .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(ex.getStatusCode()));
  }

  /**
   * Handle Entity Not Found Exception
   */
  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ErrorResponseDto> handleEntityNotFoundException(
      EntityNotFoundException ex,
      WebRequest request) {
k
    ErrorResponseDto errorResponse = ErrorResponseDto.builder()
        .message(messageService.getMessage(MessageKeys.ERROR_RESOURCE_NOT_FOUND))
        .status(HttpStatus.NOT_FOUND.value())
        .errorCode(ErrorCode.NOT_FOUND)
        .timestamp(LocalDateTime.now())
        .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  /**
   * Handle Validation Exception
   */
  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<ErrorResponseDto> handleValidationException(
      ValidationException ex,
      WebRequest request) {

    ErrorResponseDto errorResponse = ErrorResponseDto.builder()
        .message(ex.getMessage())
        .status(HttpStatus.BAD_REQUEST.value())
        .errorCode(ErrorCode.VALIDATION_ERROR)
        .timestamp(LocalDateTime.now())
        .build();

    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle All Other Exceptions (Generic)
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponseDto> handleGlobalException(
      Exception ex,
      WebRequest request) {

    ErrorResponseDto errorResponse = ErrorResponseDto.builder()
        .message(messageService.getMessage(MessageKeys.ERROR_INTERNAL_SERVER_ERROR))
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .errorCode(ErrorCode.INTERNAL_SERVER_ERROR)
        .timestamp(LocalDateTime.now())
        .build();

    ex.printStackTrace();
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
