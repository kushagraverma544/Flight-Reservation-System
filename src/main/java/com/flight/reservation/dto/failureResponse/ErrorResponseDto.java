package com.flight.reservation.dto.failureResponse;

import java.time.LocalDateTime;

import com.flight.reservation.constants.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponseDto {
  private String message;
  private int status;
  private ErrorCode errorCode;
  private LocalDateTime timestamp;
}
