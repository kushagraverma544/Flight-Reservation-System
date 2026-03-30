package com.flight.reservation.constants;

public class MessageKeys {

  // ==================== Success Messages ====================
  public static final String SUCCESS_USER_CREATED = "success.user.created";
  public static final String SUCCESS_USER_UPDATED = "success.user.updated";
  public static final String SUCCESS_USER_DELETED = "success.user.deleted";
  public static final String SUCCESS_USER_LOGIN = "success.user.login";
  public static final String SUCCESS_USER_LOGOUT = "success.user.logout";
  public static final String SUCCESS_USER_FOUND = "success.user.found";

  public static final String SUCCESS_AIRLINE_CREATED = "success.airline.created";
  public static final String SUCCESS_AIRLINE_UPDATED = "success.airline.updated";
  public static final String SUCCESS_AIRLINE_DELETED = "success.airline.deleted";
  public static final String SUCCESS_AIRLINE_DETAILS_CREATED = "success.airline.details.created";
  public static final String SUCCESS_AIRLINE_DETAILS_UPDATED = "success.airline.details.updated";

  public static final String SUCCESS_AIRPORT_CREATED = "success.airport.created";
  public static final String SUCCESS_AIRPORT_UPDATED = "success.airport.updated";
  public static final String SUCCESS_AIRPORT_DELETED = "success.airport.deleted";
  public static final String SUCCESS_AIRPORT_DETAILS_CREATED = "success.airport.details.created";
  public static final String SUCCESS_AIRPORT_DETAILS_UPDATED = "success.airport.details.updated";

  public static final String SUCCESS_FLIGHT_CREATED = "success.flight.created";
  public static final String SUCCESS_FLIGHT_UPDATED = "success.flight.updated";
  public static final String SUCCESS_FLIGHT_DELETED = "success.flight.deleted";

  public static final String SUCCESS_FLIGHT_SCHEDULE_CREATED = "success.flight.schedule.created";
  public static final String SUCCESS_FLIGHT_SCHEDULE_UPDATED = "success.flight.schedule.updated";
  public static final String SUCCESS_FLIGHT_SCHEDULE_DELETED = "success.flight.schedule.deleted";

  public static final String SUCCESS_BOOKING_CREATED = "success.booking.created";
  public static final String SUCCESS_BOOKING_CANCELLED = "success.booking.cancelled";
  public static final String SUCCESS_BOOKING_RETRIEVE = "success.booking.retrieve";

  public static final String SUCCESS_PAYMENT_PROCESSED = "success.payment.processed";
  public static final String SUCCESS_PAYMENT_COMPLETED = "success.payment.completed";
  public static final String SUCCESS_PAYMENT_FAILED = "success.payment.failed";

  // ==================== Error Messages ====================
  public static final String ERROR_USER_NOT_FOUND = "error.user.not.found";
  public static final String ERROR_USER_EMAIL_ALREADY_EXISTS = "error.user.email.already.exists";
  public static final String ERROR_USER_INVALID_CREDENTIALS = "error.user.invalid.credentials";
  public static final String ERROR_USER_UNAUTHORIZED = "error.user.unauthorized";
  public static final String ERROR_USER_FORBIDDEN = "error.user.forbidden";

  public static final String ERROR_AIRLINE_NOT_FOUND = "error.airline.not.found";
  public static final String ERROR_AIRLINE_CODE_ALREADY_EXISTS = "error.airline.code.already.exists";
  public static final String ERROR_AIRLINE_DETAILS_ALREADY_EXISTS = "error.airline.details.already.exists";

  public static final String ERROR_AIRPORT_NOT_FOUND = "error.airport.not.found";
  public static final String ERROR_AIRPORT_CODE_ALREADY_EXISTS = "error.airport.code.already.exists";
  public static final String ERROR_AIRPORT_DETAILS_ALREADY_EXISTS = "error.airport.details.already.exists";

  public static final String ERROR_FLIGHT_NOT_FOUND = "error.flight.not.found";
  public static final String ERROR_FLIGHT_NUMBER_ALREADY_EXISTS = "error.flight.number.already.exists";
  public static final String ERROR_FLIGHT_INVALID_ROUTE = "error.flight.invalid.route";

  public static final String ERROR_FLIGHT_SCHEDULE_NOT_FOUND = "error.flight.schedule.not.found";
  public static final String ERROR_FLIGHT_SCHEDULE_NO_SEATS = "error.flight.schedule.no.seats";
  public static final String ERROR_FLIGHT_SCHEDULE_INVALID = "error.flight.schedule.invalid";
  public static final String ERROR_FLIGHT_SCHEDULE_DATE_INVALID = "error.flight.schedule.date.invalid";

  public static final String ERROR_BOOKING_NOT_FOUND = "error.booking.not.found";
  public static final String ERROR_BOOKING_PNR_ALREADY_EXISTS = "error.booking.pnr.already.exists";
  public static final String ERROR_BOOKING_INVALID_REQUEST = "error.booking.invalid.request";
  public static final String ERROR_BOOKING_SEATS_UNAVAILABLE = "error.booking.seats.unavailable";
  public static final String ERROR_BOOKING_CANNOT_CANCEL = "error.booking.cannot.cancel";

  public static final String ERROR_PAYMENT_NOT_FOUND = "error.payment.not.found";
  public static final String ERROR_PAYMENT_ALREADY_EXISTS = "error.payment.already.exists";
  public static final String ERROR_PAYMENT_INVALID_AMOUNT = "error.payment.invalid.amount";
  public static final String ERROR_PAYMENT_FAILED = "error.payment.failed";
  public static final String ERROR_PAYMENT_TIMEOUT = "error.payment.timeout";

  public static final String ERROR_DATABASE_ERROR = "error.database.error";
  public static final String ERROR_VALIDATION_FAILED = "error.validation.failed";
  public static final String ERROR_INVALID_REQUEST = "error.invalid.request";
  public static final String ERROR_INTERNAL_SERVER_ERROR = "error.internal.server.error";
  public static final String ERROR_RESOURCE_NOT_FOUND = "error.resource.not.found";
  public static final String ERROR_METHOD_NOT_ALLOWED = "error.method.not.allowed";

  // ==================== Info Messages ====================
  public static final String INFO_OPERATION_IN_PROGRESS = "info.operation.in.progress";
  public static final String INFO_DATA_RETRIEVAL = "info.data.retrieval";
}
