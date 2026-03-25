package com.flight.reservation.repository;

import com.flight.reservation.entity.BookingPassengerEntity;
import com.flight.reservation.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingPassengerRepository extends JpaRepository<BookingPassengerEntity, Long> {

  List<BookingPassengerEntity> findByBooking(BookingEntity booking);

  List<BookingPassengerEntity> findByBookingId(Long bookingId);
}
