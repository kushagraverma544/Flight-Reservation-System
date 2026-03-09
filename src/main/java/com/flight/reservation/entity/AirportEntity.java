package com.flight.reservation.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "airports",
        indexes = {
                @Index(name = "idx_airport_code", columnList = "airport_code")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportEntity extends BaseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "airport_code", nullable = false, unique = true, length = 3)
    private String airportCode;

    @Column(name = "airport_name",nullable = false, length = 200)
    private String airportName;

    @Column(nullable = false, length = 100)
    private String city;

    @Column(nullable = false, length = 100)
    private String country;

}