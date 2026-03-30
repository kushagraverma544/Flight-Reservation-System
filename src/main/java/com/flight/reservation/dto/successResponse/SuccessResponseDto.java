package com.flight.reservation.dto.successResponse;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponseDto<T> {
    private String message;
    private T data;
    private int status;
    private LocalDateTime  timestamp;
}
