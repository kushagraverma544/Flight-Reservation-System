package com.flight.reservation.repository;

import com.flight.reservation.entity.AirlineEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirlineRepository extends JpaRepository<AirlineEntity, Long> {

  Optional<AirlineEntity> findByAirlineCode(String airlineCode);

  boolean existsByAirlineCode(String airlineCode);
}
