package com.library.service;

import java.util.List;
import java.util.UUID;

import com.library.model.Reservation;
import org.springframework.stereotype.Service;

@Service
public interface ReservationService extends RepositoryService<Reservation> {

	List<Reservation> getByUserId(UUID userId);

	void cancel(UUID id);

	List<Reservation> getByBookId(UUID id);

}
