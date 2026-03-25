package com.flight.reservation.repository;

import com.flight.reservation.entity.AirportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportRepository extends JpaRepository<AirportEntity, Long> {

  Optional<AirportEntity> findByAirportCode(String airportCode);

  boolean existsByAirportCode(String airportCode);
}
