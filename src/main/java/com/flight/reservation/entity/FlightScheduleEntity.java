package com.flight.reservation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "flight_schedules",
        indexes = {
                @Index(name = "idx_flight_schedule_date", columnList = "flight_date")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightScheduleEntity extends BaseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Flight FK
    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private FlightEntity flight;

    // Flight Date
    @Column(name = "flight_date", nullable = false)
    private LocalDate flightDate;

    // Departure time
    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureTime;

    // Arrival time
    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalTime;

    // Seat management
    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

}

/*
🔗 FK Relationships
        | Column    | Reference  |
        | --------- | ---------- |
        | flight_id | flights.id |
 */