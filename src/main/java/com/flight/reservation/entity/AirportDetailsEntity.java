package com.flight.reservation.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "airport_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirportDetailsEntity extends BaseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "airport_id", nullable = false, unique = true)
    private AirportEntity airport;

    @Column(name = "number_of_terminals", nullable = false)
    private Integer numberOfTerminals;

    @Column(name = "passenger_capacity", nullable = false)
    private Long passengerCapacity;

    @Column(name = "runway_count", nullable = false)
    private Long runwayCount;

    @Column(name = "airport_address", length = 500)
    private String airportAddress;

}