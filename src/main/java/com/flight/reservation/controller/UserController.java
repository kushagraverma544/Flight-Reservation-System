package com.flight.reservation.controller;

import com.flight.reservation.common.ResponseBuilder;
import com.flight.reservation.constants.MessageKeys;
import com.flight.reservation.dto.failureResponse.ErrorResponseDto;
import com.flight.reservation.dto.successResponse.SuccessResponseDto;
import com.flight.reservation.dto.user.UserRequestDto;
import com.flight.reservation.dto.user.UserResponseDto;
import com.flight.reservation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

  private final UserService userService;
  private final ResponseBuilder responseBuilder;

  public UserController(UserService userService, ResponseBuilder responseBuilder) {
    this.userService = userService;
    this.responseBuilder = responseBuilder;
  }

  /**
   * Register a new user
   * POST /api/users/register
   *
   * @param userRequestDto - User registration request DTO
   * @return - Success response with registered user details
   */
  @PostMapping("/register")
  public ResponseEntity<SuccessResponseDto<UserResponseDto>> registerUser(
      @RequestBody UserRequestDto userRequestDto) {

    log.info("Incoming request to register user with email: {}", userRequestDto.getEmail());

    try {
      UserResponseDto registeredUser = userService.registerUser(userRequestDto);
      log.info("User registered successfully with ID: {}", registeredUser.getId());

      return ResponseEntity.status(HttpStatus.CREATED).body(
          responseBuilder.buildCreated(MessageKeys.SUCCESS_USER_CREATED, registeredUser));
    } catch (Exception e) {
      log.error("Error registering user: {}", e.getMessage(), e);
      throw e;
    }
  }

  /**
   * Get user by ID
   * GET /api/users/{id}
   *
   * @param userId - User ID
   * @return - Success response with user details
   */
  @GetMapping("/{id}")
  public ResponseEntity<SuccessResponseDto<UserResponseDto>> getUserById(
      @PathVariable Long userId) {

    log.info("Fetching user with ID: {}", userId);

    UserResponseDto user = userService.getUserById(userId);
    return ResponseEntity.ok(
        responseBuilder.buildSuccess(MessageKeys.INFO_DATA_RETRIEVAL, user));
  }

  /**
   * Get user by email
   * GET /api/users/email/{email}
   *
   * @param email - User email
   * @return - Success response with user details
   */
  @GetMapping("/email/{email}")
  public ResponseEntity<SuccessResponseDto<UserResponseDto>> getUserByEmail(
      @PathVariable String email) {

    log.info("Fetching user with email: {}", email);

    UserResponseDto user = userService.getUserByEmail(email);
    return ResponseEntity.ok(
        responseBuilder.buildSuccess(MessageKeys.INFO_DATA_RETRIEVAL, user));
  }

  /**
   * Update user
   * PUT /api/users/{id}
   *
   * @param userId         - User ID
   * @param userRequestDto - User update request DTO
   * @return - Success response with updated user details
   */
  @PutMapping("/{id}")
  public ResponseEntity<SuccessResponseDto<UserResponseDto>> updateUser(
      @PathVariable Long userId,
      @RequestBody UserRequestDto userRequestDto) {

    log.info("Updating user with ID: {}", userId);

    UserResponseDto updatedUser = userService.updateUser(userId, userRequestDto);
    return ResponseEntity.ok(
        responseBuilder.buildSuccess(MessageKeys.SUCCESS_USER_UPDATED, updatedUser));
  }

  /**
   * Delete user
   * DELETE /api/users/{id}
   *
   * @param userId - User ID
   * @return - Success response
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<SuccessResponseDto<Void>> deleteUser(
      @PathVariable Long userId) {

    log.info("Deleting user with ID: {}", userId);

    userService.deleteUser(userId);
    return ResponseEntity.ok(
        responseBuilder.buildSuccess(MessageKeys.SUCCESS_USER_DELETED));
  }

  /**
   * Check if user exists by email
   * GET /api/users/exists/{email}
   *
   * @param email - User email
   * @return - Success response with boolean flag
   */
  @GetMapping("/exists/{email}")
  public ResponseEntity<SuccessResponseDto<Boolean>> checkUserExists(
      @PathVariable String email) {

    log.info("Checking if user exists with email: {}", email);

    boolean exists = userService.isUserExists(email);
    return ResponseEntity.ok(
        responseBuilder.buildSuccess(MessageKeys.INFO_DATA_RETRIEVAL, exists));
  }
}
