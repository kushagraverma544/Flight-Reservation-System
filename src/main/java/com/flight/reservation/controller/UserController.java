package com.flight.reservation.controller;

import com.flight.reservation.common.ResponseBuilder;
import com.flight.reservation.constants.MessageKeys;
import com.flight.reservation.dto.successResponse.SuccessResponseDto;
import com.flight.reservation.dto.user.UserRequestDto;
import com.flight.reservation.dto.user.UserResponseDto;
import com.flight.reservation.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserService userService;
  private final ResponseBuilder responseBuilder;

  /**
   * Register a new user
   * POST /api/users/register
   */
  @PostMapping("/register")
  public ResponseEntity<SuccessResponseDto<UserResponseDto>> registerUser(
      @Valid @RequestBody UserRequestDto userRequestDto) {

    log.info("Registering user with email: {}", userRequestDto.getEmail());

    UserResponseDto registeredUser = userService.registerUser(userRequestDto);

    log.info("User registered successfully with ID: {}", registeredUser.getId());

    return ResponseEntity.status(HttpStatus.CREATED).body(
        responseBuilder.buildCreated(
            MessageKeys.SUCCESS_USER_CREATED,
            registeredUser));
  }

  /**
   * Get user by ID
   * GET /api/users/{id}
   */
  @GetMapping("/{id}")
  public ResponseEntity<SuccessResponseDto<UserResponseDto>> getUserById(
      @PathVariable Long id) {

    log.info("Fetching user with ID: {}", id);

    UserResponseDto user = userService.getUserById(id);

    return ResponseEntity.ok(
        responseBuilder.buildSuccess(
            MessageKeys.INFO_DATA_RETRIEVAL,
            user));
  }

  /**
   * Get user by email
   * GET /api/users/by-email?email=
   */
  @GetMapping("/by-email")
  public ResponseEntity<SuccessResponseDto<UserResponseDto>> getUserByEmail(
      @RequestParam String email) {

    log.info("Fetching user with email: {}", email);

    UserResponseDto user = userService.getUserByEmail(email);

    return ResponseEntity.ok(
        responseBuilder.buildSuccess(
            MessageKeys.INFO_DATA_RETRIEVAL,
            user));
  }

  /**
   * Update user
   * PUT /api/users/{id}
   */
  @PutMapping("/{id}")
  public ResponseEntity<SuccessResponseDto<UserResponseDto>> updateUser(
      @PathVariable Long id,
      @Valid @RequestBody UserRequestDto userRequestDto) {

    log.info("Updating user with ID: {}", id);

    UserResponseDto updatedUser = userService.updateUser(id, userRequestDto);

    return ResponseEntity.ok(
        responseBuilder.buildSuccess(
            MessageKeys.SUCCESS_USER_UPDATED,
            updatedUser));
  }

  /**
   * Delete user
   * DELETE /api/users/{id}
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<SuccessResponseDto<Void>> deleteUser(
      @PathVariable Long id) {

    log.info("Deleting user with ID: {}", id);

    userService.deleteUser(id);

    return ResponseEntity.ok(
        responseBuilder.buildSuccess(
            MessageKeys.SUCCESS_USER_DELETED));
  }

  /**
   * Check if user exists
   * GET /api/users/exists?email=
   */
  @GetMapping("/exists")
  public ResponseEntity<SuccessResponseDto<Boolean>> checkUserExists(
      @RequestParam String email) {

    log.info("Checking if user exists with email: {}", email);

    boolean exists = userService.isUserExists(email);

    return ResponseEntity.ok(
        responseBuilder.buildSuccess(
            MessageKeys.SUCCESS_USER_FOUND,
            exists));
  }
}