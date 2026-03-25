package com.flight.reservation.repository;

import com.flight.reservation.entity.AirlineDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirlineDetailsRepository extends JpaRepository<AirlineDetailsEntity, Long> {

  Optional<AirlineDetailsEntity> findByAirlineId(Long airlineId);

  boolean existsByAirlineId(Long airlineId);
}
