package com.flight.reservation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "bookings",
        indexes = {
                @Index(name = "idx_booking_pnr", columnList = "pnr_number")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingEntity extends BaseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // PNR number
    @Column(name = "pnr_number", nullable = false, unique = true, length = 10)
    private String pnrNumber;

    // User FK
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    // Flight Schedule FK
    @ManyToOne
    @JoinColumn(name = "flight_schedule_id", nullable = false)
    private FlightScheduleEntity flightSchedule;

    // Booking timestamp
    @Column(name = "booking_time", nullable = false)
    private LocalDateTime bookingTime;

    // Total seats booked
    @Column(name = "seat_count", nullable = false)
    private Integer seatCount;

    // Total price
    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

}

/*
* Database Table
            | id | pnr_number | user_id | flight_schedule_id | booking_time | seat_count | total_price |
            | -- | ---------- | ------- | ------------------ | ------------ | ---------- | ----------- |

* Example:
            PNR	    User	    Flight	Seats
            AB12CD	Kushagra	6E101	2

* Foreign Key
            | Column             | Reference           |
            | ------------------ | ------------------- |
            | user_id            | users.id            |
            | flight_schedule_id | flight_schedules.id |

*/