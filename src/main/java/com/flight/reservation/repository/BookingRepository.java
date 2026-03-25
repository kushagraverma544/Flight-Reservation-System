package com.flight.reservation.repository;

import com.flight.reservation.entity.BookingEntity;
import com.flight.reservation.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

  Optional<BookingEntity> findByPnrNumber(String pnrNumber);

  boolean existsByPnrNumber(String pnrNumber);

  List<BookingEntity> findByUser(UserEntity user);

  List<BookingEntity> findByUserId(Long userId);
}
