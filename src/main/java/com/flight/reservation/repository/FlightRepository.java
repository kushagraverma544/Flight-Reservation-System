package com.flight.reservation.repository;

import com.flight.reservation.entity.FlightEntity;
import com.flight.reservation.entity.AirlineEntity;
import com.flight.reservation.entity.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<FlightEntity, Long> {

  Optional<FlightEntity> findByFlightNumber(String flightNumber);

  boolean existsByFlightNumber(String flightNumber);

  List<FlightEntity> findByAirline(AirlineEntity airline);

  List<FlightEntity> findBySourceAirport(AirportEntity sourceAirport);

  List<FlightEntity> findByDestinationAirport(AirportEntity destinationAirport);

  List<FlightEntity> findBySourceAirportAndDestinationAirport(AirportEntity sourceAirport,
      AirportEntity destinationAirport);
}
