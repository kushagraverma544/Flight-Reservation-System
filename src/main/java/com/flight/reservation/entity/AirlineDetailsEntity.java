package com.flight.reservation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "airline_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AirlineDetailsEntity extends BaseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "airline_id", nullable = false, unique = true)
    private AirlineEntity airline;

    @Column(name = "contact_email", nullable = false, length = 150)
    private String contactEmail;

    @Column(name = "contact_number",nullable = false, length = 20)
    private String contactNumber;

    @Column(nullable = false)
    private String headquarters;

    @Column(name="registration_number",nullable = false, length = 50)
    private String registrationNumber;

    // Approval Tracking
    @ManyToOne
    @JoinColumn(name = "approved_by")
    private UserEntity approvedBy;

    @Column(name = "approved_at")
    private LocalDateTime approvedAt;

    @Column(name = "rejection_reason", length = 500)
    private String rejectionReason;

}