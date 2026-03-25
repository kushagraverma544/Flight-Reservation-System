package com.flight.reservation.service;

import com.flight.reservation.dto.user.UserRequestDto;
import com.flight.reservation.dto.user.UserResponseDto;
import com.flight.reservation.entity.UserEntity;

import java.util.Optional;

public interface UserService {

  /**
   * Register a new user
   *
   * @param userRequestDto - User registration request DTO
   * @return - UserResponseDto with registered user details
   */
  UserResponseDto registerUser(UserRequestDto userRequestDto);

  /**
   * Get user by ID
   *
   * @param userId - User ID
   * @return - UserResponseDto if found
   */
  UserResponseDto getUserById(Long userId);

  /**
   * Get user by email
   *
   * @param email - User email
   * @return - UserResponseDto if found
   */
  UserResponseDto getUserByEmail(String email);

  /**
   * Check if user exists by email
   *
   * @param email - User email
   * @return - true if user exists, false otherwise
   */
  boolean isUserExists(String email);

  /**
   * Get user entity by ID
   *
   * @param userId - User ID
   * @return - Optional containing UserEntity if found
   */
  Optional<UserEntity> findUserById(Long userId);

  /**
   * Get user entity by email
   *
   * @param email - User email
   * @return - Optional containing UserEntity if found
   */
  Optional<UserEntity> findUserByEmail(String email);

  /**
   * Update user
   *
   * @param userId         - User ID
   * @param userRequestDto - User update request DTO
   * @return - Updated UserResponseDto
   */
  UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto);

  /**
   * Delete user by ID
   *
   * @param userId - User ID
   */
  void deleteUser(Long userId);
}
