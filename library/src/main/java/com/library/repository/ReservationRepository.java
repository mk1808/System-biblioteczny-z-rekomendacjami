package com.library.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.model.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
	List<Reservation> getByUserId(UUID userId);
}
