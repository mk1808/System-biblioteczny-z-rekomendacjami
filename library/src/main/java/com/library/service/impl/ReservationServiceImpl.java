package com.library.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.model.Borrowing;
import com.library.model.Reservation;
import com.library.repository.ReservationRepository;
import com.library.service.BookService;
import com.library.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	private final ReservationRepository repository;
	
	@Autowired
	public ReservationServiceImpl(ReservationRepository repository, BookService bookService) {
		this.repository = repository;
	}
	
	@Override
	public Reservation get(UUID id) {
		return repository.getById(id);
	}

	@Override
	public Reservation create(Reservation entity) {
		return repository.save(entity);
	}

	@Override
	public Reservation update(Reservation entity) {
		return repository.save(entity);
	}

	@Override
	public List<Reservation> getByUserId(UUID userId) {

		return repository.getByUserId(userId)
				.stream()
				.filter(this::wasNotBorrowed)
				.collect(Collectors.toList());
	}
	
	private Boolean wasNotBorrowed(Reservation reservation) {
		return reservation.getWasBorrowed()==null||reservation.getWasBorrowed()==false;
	}

	@Override
	public void cancel(UUID id) {
		// TODO Auto-generated method stub
		
	}

}
