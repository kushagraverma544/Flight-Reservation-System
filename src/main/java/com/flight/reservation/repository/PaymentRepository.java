package com.flight.reservation.repository;

import com.flight.reservation.entity.PaymentEntity;
import com.flight.reservation.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

  Optional<PaymentEntity> findByBooking(BookingEntity booking);

  Optional<PaymentEntity> findByBookingId(Long bookingId);

  Optional<PaymentEntity> findByTransactionId(String transactionId);

  boolean existsByTransactionId(String transactionId);
}
