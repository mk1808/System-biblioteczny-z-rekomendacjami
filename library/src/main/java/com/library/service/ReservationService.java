package com.library.service;

import java.util.List;

import com.library.model.Reservation;

public interface ReservationService extends RepositoryService<Reservation> {

	List<Reservation> getByUserId(Long userId);

	void cancel(Long id);

}
