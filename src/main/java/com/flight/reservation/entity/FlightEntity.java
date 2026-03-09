package com.flight.reservation.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "flights",
        indexes = {
                @Index(name = "idx_flight_number", columnList = "flight_number")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FlightEntity extends BaseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "flight_number", nullable = false, unique = true, length = 10)
    private String flightNumber;

    // Airline FK
    @ManyToOne
    @JoinColumn(name = "airline_id", nullable = false)
    private AirlineEntity airline;

    // Source Airport FK
    @ManyToOne
    @JoinColumn(name = "source_airport_id", nullable = false)
    private AirportEntity sourceAirport;

    // Destination Airport FK
    @ManyToOne
    @JoinColumn(name = "destination_airport_id", nullable = false)
    private AirportEntity destinationAirport;

}


/*
        | Column                 | Reference   |
        | ---------------------- | ----------- |
        | airline_id             | airlines.id |
        | source_airport_id      | airports.id |
        | destination_airport_id | airports.id |
 */