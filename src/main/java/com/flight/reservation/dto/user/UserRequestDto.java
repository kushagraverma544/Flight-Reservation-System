package com.flight.reservation.dto.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {
    private String name;
    private String email;
    private String password;
    private String role;

}
