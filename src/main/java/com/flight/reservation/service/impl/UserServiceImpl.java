package com.flight.reservation.service.impl;

import com.flight.reservation.constants.MessageKeys;
import com.flight.reservation.constants.UserRolesEnum;
import com.flight.reservation.constants.WorkingStatusEnum;
import com.flight.reservation.dto.user.UserRequestDto;
import com.flight.reservation.dto.user.UserResponseDto;
import com.flight.reservation.entity.UserEntity;
import com.flight.reservation.exception.EntityNotFoundException;
import com.flight.reservation.exception.ValidationException;
import com.flight.reservation.repository.UserRepository;
import com.flight.reservation.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  /**
   * Register a new user with validation and password encoding
   */
  @Override
  public UserResponseDto registerUser(UserRequestDto userRequestDto) {
    log.info("Attempting to register user with email: {}", userRequestDto.getEmail());

    // Validate request
    validateUserRequest(userRequestDto);

    // Check if email already exists
    if (isUserExists(userRequestDto.getEmail())) {
      log.warn("Registration failed: Email {} already exists", userRequestDto.getEmail());
      throw new ValidationException(
          "email",
          "Email already registered",
          MessageKeys.ERROR_USER_EMAIL_ALREADY_EXISTS);
    }

    try {
      // Create new user entity
      UserEntity userEntity = UserEntity.builder()
          .name(userRequestDto.getName())
          .email(userRequestDto.getEmail())
          .password(passwordEncoder.encode(userRequestDto.getPassword()))
          .role(UserRolesEnum.valueOf(userRequestDto.getRole().toUpperCase()))
          .active(WorkingStatusEnum.ACTIVE)
          .build();

      // Save user to database
      UserEntity savedUser = userRepository.save(userEntity);
      log.info("User registered successfully with ID: {}", savedUser.getId());

      // Convert to response DTO
      return mapToResponseDto(savedUser);

    } catch (Exception e) {
      log.error("Error during user registration: {}", e.getMessage(), e);
      throw new RuntimeException("Error registering user", e);
    }
  }

  /**
   * Validate user registration request
   */
  private void validateUserRequest(UserRequestDto userRequestDto) {
    // Validate name
    if (userRequestDto.getName() == null || userRequestDto.getName().trim().isEmpty()) {
      throw new ValidationException("name", "Name cannot be empty", MessageKeys.ERROR_VALIDATION_FAILED);
    }

    // Validate email format
    if (userRequestDto.getEmail() == null || !isValidEmail(userRequestDto.getEmail())) {
      throw new ValidationException("email", "Invalid email format", MessageKeys.ERROR_VALIDATION_FAILED);
    }

    // Validate password
    if (userRequestDto.getPassword() == null || userRequestDto.getPassword().length() < 6) {
      throw new ValidationException("password", "Password must be at least 6 characters",
          MessageKeys.ERROR_VALIDATION_FAILED);
    }

    // Validate role
    if (userRequestDto.getRole() == null || userRequestDto.getRole().trim().isEmpty()) {
      throw new ValidationException("role", "Role cannot be empty", MessageKeys.ERROR_VALIDATION_FAILED);
    }

    // Check if role is valid
    try {
      UserRolesEnum.valueOf(userRequestDto.getRole().toUpperCase());
    } catch (IllegalArgumentException e) {
      throw new ValidationException("role", "Invalid role", MessageKeys.ERROR_VALIDATION_FAILED);
    }
  }

  /**
   * Validate email format
   */
  private boolean isValidEmail(String email) {
    String emailRegex = "^[A-Za-z0-9+_.-]+@+[A-Za-z0-9.-]+$";
    return email.matches(emailRegex);
  }

  /**
   * Get user by ID
   */
  @Override
  @Transactional(readOnly = true)
  public UserResponseDto getUserById(Long userId) {
    log.info("Fetching user with ID: {}", userId);
    UserEntity userEntity = userRepository.findById(userId)
        .orElseThrow(() -> {
          log.warn("User not found with ID: {}", userId);
          return new EntityNotFoundException("User", String.valueOf(userId));
        });
    return mapToResponseDto(userEntity);
  }

  /**
   * Get user by email
   */
  @Override
  @Transactional(readOnly = true)
  public UserResponseDto getUserByEmail(String email) {
    log.info("Fetching user with email: {}", email);
    UserEntity userEntity = userRepository.findByEmail(email)
        .orElseThrow(() -> {
          log.warn("User not found with email: {}", email);
          return new EntityNotFoundException("User", email);
        });
    return mapToResponseDto(userEntity);
  }

  /**
   * Check if user exists by email
   */
  @Override
  @Transactional(readOnly = true)
  public boolean isUserExists(String email) {
    return userRepository.existsByEmail(email);
  }

  /**
   * Find user entity by ID
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<UserEntity> findUserById(Long userId) {
    return userRepository.findById(userId);
  }

  /**
   * Find user entity by email
   */
  @Override
  @Transactional(readOnly = true)
  public Optional<UserEntity> findUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  /**
   * Update user
   */
  @Override
  public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto) {
    log.info("Updating user with ID: {}", userId);

    UserEntity userEntity = userRepository.findById(userId)
        .orElseThrow(() -> {
          log.warn("User not found with ID: {}", userId);
          return new EntityNotFoundException("User", String.valueOf(userId));
        });

    // Update name if provided
    if (userRequestDto.getName() != null && !userRequestDto.getName().trim().isEmpty()) {
      userEntity.setName(userRequestDto.getName());
    }

    // Update password if provided
    if (userRequestDto.getPassword() != null && !userRequestDto.getPassword().isEmpty()) {
      if (userRequestDto.getPassword().length() < 6) {
        throw new ValidationException("password", "Password must be at least 6 characters",
            MessageKeys.ERROR_VALIDATION_FAILED);
      }
      userEntity.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
    }

    // Update role if provided
    if (userRequestDto.getRole() != null && !userRequestDto.getRole().trim().isEmpty()) {
      try {
        userEntity.setRole(UserRolesEnum.valueOf(userRequestDto.getRole().toUpperCase()));
      } catch (IllegalArgumentException e) {
        throw new ValidationException("role", "Invalid role", MessageKeys.ERROR_VALIDATION_FAILED);
      }
    }

    UserEntity updatedUser = userRepository.save(userEntity);
    log.info("User updated successfully with ID: {}", userId);

    return mapToResponseDto(updatedUser);
  }

  /**
   * Delete user by ID
   */
  @Override
  public void deleteUser(Long userId) {
    log.info("Deleting user with ID: {}", userId);

    UserEntity userEntity = userRepository.findById(userId)
        .orElseThrow(() -> {
          log.warn("User not found with ID: {}", userId);
          return new EntityNotFoundException("User", String.valueOf(userId));
        });

    userRepository.delete(userEntity);
    log.info("User deleted successfully with ID: {}", userId);
  }

  /**
   * Map UserEntity to UserResponseDto
   */
  private UserResponseDto mapToResponseDto(UserEntity userEntity) {
    return UserResponseDto.builder()
        .id(userEntity.getId())
        .name(userEntity.getName())
        .email(userEntity.getEmail())
        .role(userEntity.getRole().name())
        .active(userEntity.getActive().name())
        .createdAt(userEntity.getCreatedAt())
        .createdBy(userEntity.getCreatedBy())
        .build();
  }
}
