package com.flight.reservation.repository;

import com.flight.reservation.entity.AirportDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirportDetailsRepository extends JpaRepository<AirportDetailsEntity, Long> {

  Optional<AirportDetailsEntity> findByAirportId(Long airportId);

  boolean existsByAirportId(Long airportId);
}
