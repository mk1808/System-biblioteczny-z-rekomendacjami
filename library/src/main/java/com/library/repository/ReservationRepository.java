package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.model.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

}
