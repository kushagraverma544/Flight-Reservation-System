package com.flight.reservation.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
        name = "payments",
        indexes = {
                @Index(name = "idx_transaction_id", columnList = "transaction_id")
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentEntity extends BaseAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Booking FK
    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false, unique = true)
    private BookingEntity booking;

    @Column(name = "transaction_id", nullable = false, unique = true, length = 100)
    private String transactionId;

    @Column(name = "payment_method", nullable = false, length = 50)
    private String paymentMethod;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "payment_status", nullable = false, length = 30)
    private String paymentStatus;

}

/*
🗄️ Database Table
    payments
        id	booking_id	transaction_id	payment_method	amount	payment_status

Example:
        booking	    method	    status
        PNR123	    UPI	        SUCCESS
*/