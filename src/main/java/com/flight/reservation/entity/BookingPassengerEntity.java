package com.flight.reservation.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "booking_passengers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingPassengerEntity extends BaseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Booking FK
    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private BookingEntity booking;

    @Column(name = "passenger_name", nullable = false, length = 150)
    private String passengerName;

    @Column(nullable = false)
    private Integer age;

    @Column(name = "seat_number", length = 5)
    private String seatNumber;

}

/*
* Database Table
    booking_passengers
        id	booking_id	passenger_name	age	seat_number

Example:
            booking	    passenger	    seat
            PNR123	    Kushagra	    12A
            PNR123	    Rahul	        12B

🔗 FK Relationship
            Column	        Reference
            booking_id	    bookings.id
 */