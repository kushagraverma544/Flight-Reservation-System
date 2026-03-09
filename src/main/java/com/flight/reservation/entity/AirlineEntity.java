package com.flight.reservation.entity;

import com.flight.reservation.constants.AirlineStatusEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "airlines")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirlineEntity extends BaseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "airline_name",nullable = false, length = 150)
    private String airlineName;

    @Column(name = "airline_code",nullable = false, unique = true, length = 5)
    private String airlineCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AirlineStatusEnum status;

}