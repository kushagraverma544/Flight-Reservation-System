package com.flight.reservation.common;

import com.flight.reservation.constants.ErrorCode;
import com.flight.reservation.dto.failureResponse.ErrorResponseDto;
import com.flight.reservation.dto.successResponse.SuccessResponseDto;
import com.flight.reservation.service.MessageService;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ResponseBuilder {

  private final MessageService messageService;

  /**
   * Build success response with message from properties
   *
   * @param messageKey - Message key from MessageKeys constants
   * @param data       - Response data
   * @param status     - HTTP status code
   * @return - SuccessResponseDto
   */
  public <T> SuccessResponseDto<T> buildSuccessResponse(String messageKey, T data, HttpStatus status) {
    String message = messageService.getMessage(messageKey);
    return SuccessResponseDto.<T>builder()
        .message(message)
        .data(data)
        .status(status.value())
        .timestamp(LocalDateTime.now())
        .build();
  }

  /**
   * Build success response with custom message
   *
   * @param customMessage - Custom message
   * @param data          - Response data
   * @param status        - HTTP status code
   * @return - SuccessResponseDto
   */
  public <T> SuccessResponseDto<T> buildSuccessResponseWithCustomMessage(String customMessage, T data,
      HttpStatus status) {
    return SuccessResponseDto.<T>builder()
        .message(customMessage)
        .data(data)
        .status(status.value())
        .timestamp(LocalDateTime.now())
        .build();
  }

  /**
   * Build success response (HTTP 200)
   *
   * @param messageKey - Message key from MessageKeys constants
   * @param data       - Response data
   * @return - SuccessResponseDto
   */
  public <T> SuccessResponseDto<T> buildSuccess(String messageKey, T data) {
    return buildSuccessResponse(messageKey, data, HttpStatus.OK);
  }

  /**
   * Build success response with only message (HTTP 200)
   *
   * @param messageKey - Message key from MessageKeys constants
   * @return - SuccessResponseDto
   */
  public <T> SuccessResponseDto<T> buildSuccess(String messageKey) {
    return buildSuccessResponse(messageKey, null, HttpStatus.OK);
  }

  /**
   * Build created response (HTTP 201)
   *
   * @param messageKey - Message key from MessageKeys constants
   * @param data       - Response data
   * @return - SuccessResponseDto
   */
  public <T> SuccessResponseDto<T> buildCreated(String messageKey, T data) {
    return buildSuccessResponse(messageKey, data, HttpStatus.CREATED);
  }

  /**
   * Build error response with message from properties
   *
   * @param messageKey - Message key from MessageKeys constants
   * @param errorCode  - Error code identifier
   * @param status     - HTTP status code
   * @return - ErrorResponseDto
   */
  public ErrorResponseDto buildErrorResponse(String messageKey, ErrorCode errorCode, HttpStatus status) {
    String message = messageService.getMessage(messageKey);
    return ErrorResponseDto.builder()
        .message(message)
        .status(status.value())
        .errorCode(errorCode)
        .timestamp(LocalDateTime.now())
        .build();
  }

  /**
   * Build error response with custom message
   *
   * @param customMessage - Custom message
   * @param errorCode     - Error code identifier
   * @param status        - HTTP status code
   * @return - ErrorResponseDto
   */
  public ErrorResponseDto buildErrorResponseWithCustomMessage(String customMessage, ErrorCode errorCode,
      HttpStatus status) {
    return ErrorResponseDto.builder()
        .message(customMessage)
        .status(status.value())
        .errorCode(errorCode)
        .timestamp(LocalDateTime.now())
        .build();
  }

  /**
   * Build bad request error response (HTTP 400)
   *
   * @param messageKey - Message key from MessageKeys constants
   * @return - ErrorResponseDto
   */
  public ErrorResponseDto buildBadRequest(String messageKey) {
    return buildErrorResponse(messageKey, ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
  }

  /**
   * Build not found error response (HTTP 404)
   *
   * @param messageKey - Message key from MessageKeys constants
   * @return - ErrorResponseDto
   */
  public ErrorResponseDto buildNotFound(String messageKey) {
    return buildErrorResponse(messageKey, ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND);
  }

  /**
   * Build unauthorized error response (HTTP 401)
   *
   * @param messageKey - Message key from MessageKeys constants
   * @return - ErrorResponseDto
   */
  public ErrorResponseDto buildUnauthorized(String messageKey) {
    return buildErrorResponse(messageKey, ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
  }

  /**
   * Build forbidden error response (HTTP 403)
   *
   * @param messageKey - Message key from MessageKeys constants
   * @return - ErrorResponseDto
   */
  public ErrorResponseDto buildForbidden(String messageKey) {
    return buildErrorResponse(messageKey, ErrorCode.FORBIDDEN, HttpStatus.FORBIDDEN);
  }

  /**
   * Build internal server error response (HTTP 500)
   *
   * @param messageKey - Message key from MessageKeys constants
   * @return - ErrorResponseDto
   */
  public ErrorResponseDto buildInternalServerError(String messageKey) {
    return buildErrorResponse(messageKey, ErrorCode.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
