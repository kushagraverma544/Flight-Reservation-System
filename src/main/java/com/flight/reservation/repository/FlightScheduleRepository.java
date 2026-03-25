package com.flight.reservation.repository;

import com.flight.reservation.entity.FlightScheduleEntity;
import com.flight.reservation.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface FlightScheduleRepository extends JpaRepository<FlightScheduleEntity, Long> {

  List<FlightScheduleEntity> findByFlight(FlightEntity flight);

  List<FlightScheduleEntity> findByFlightDate(LocalDate flightDate);

  List<FlightScheduleEntity> findByFlightAndFlightDate(FlightEntity flight, LocalDate flightDate);

  List<FlightScheduleEntity> findByFlightDateAndAvailableSeatsGreaterThan(LocalDate flightDate, Integer availableSeats);
}
