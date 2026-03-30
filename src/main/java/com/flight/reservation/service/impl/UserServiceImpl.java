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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // ================= REGISTER =================

    @Override
    public UserResponseDto registerUser(UserRequestDto dto) {
        log.info("Registering user with email: {}", dto.getEmail());
        String email = dto.getEmail().trim().toLowerCase();
        if (userRepository.existsByEmail(email)) {
            throw new ValidationException(
                    "email",
                    "Email already registered",
                    MessageKeys.ERROR_USER_EMAIL_ALREADY_EXISTS
            );
        }
        UserRolesEnum role = parseRole(dto.getRole());

        UserEntity user = UserEntity.builder()
                .name(dto.getName().trim())
                .email(email)
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(role)
                .active(WorkingStatusEnum.ACTIVE)
                .build();

        UserEntity savedUser = userRepository.save(user);

        log.info("User created with ID: {}", savedUser.getId());

        return mapToResponseDto(savedUser);
    }

    // ================= GET =================

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserById(Long userId) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User", String.valueOf(userId)));

        return mapToResponseDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDto getUserByEmail(String email) {

        UserEntity user = userRepository.findByEmail(email.trim().toLowerCase())
                .orElseThrow(() -> new EntityNotFoundException("User", email));

        return mapToResponseDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserExists(String email) {
        return userRepository.existsByEmail(email.trim().toLowerCase());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserEntity> findUserByEmail(String email) {
        return userRepository.findByEmail(email.trim().toLowerCase());
    }

    // ================= UPDATE =================
    @Override
    public UserResponseDto updateUser(Long userId, UserRequestDto dto) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User", String.valueOf(userId)));

        // Name
        if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
            user.setName(dto.getName().trim());
        }
        // Password
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            if (dto.getPassword().length() < 6) {
                throw new ValidationException(
                        "password",
                        "Password must be at least 6 characters",
                        MessageKeys.ERROR_VALIDATION_FAILED
                );
            }
            user.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        // Role
        if (dto.getRole() != null && !dto.getRole().isBlank()) {
            user.setRole(parseRole(dto.getRole()));
        }

        UserEntity updatedUser = userRepository.save(user);

        log.info("User updated with ID: {}", userId);

        return mapToResponseDto(updatedUser);
    }

    // ================= DELETE (SOFT DELETE) =================
    @Override
    public void deleteUser(Long userId) {

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User", String.valueOf(userId)));

        user.setActive(WorkingStatusEnum.INACTIVE);

        userRepository.save(user);

        log.info("User soft deleted with ID: {}", userId);
    }

    // ================= HELPER METHODS =================

    private UserRolesEnum parseRole(String role) {
        try {
            return UserRolesEnum.valueOf(role.trim().toUpperCase());
        } catch (Exception e) {
            throw new ValidationException(
                    "role",
                    "Invalid role",
                    MessageKeys.ERROR_VALIDATION_FAILED
            );
        }
    }

    private UserResponseDto mapToResponseDto(UserEntity user) {
        return UserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .active(user.getActive().name())
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .build();
    }
}