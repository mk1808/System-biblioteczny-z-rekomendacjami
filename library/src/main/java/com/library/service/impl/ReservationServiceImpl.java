package com.library.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.model.Borrowing;
import com.library.model.Reservation;
import com.library.repository.ReservationRepository;
import com.library.service.BookService;
import com.library.service.UserService;
import com.library.service.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService {
	private final ReservationRepository repository;
	private BookService bookService;
	private UserService userService;
	
	@Autowired
	public ReservationServiceImpl(ReservationRepository repository) {
		this.repository = repository;
	}
	
    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }
    
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
	
	@Override
	public Reservation get(UUID id) {
		return repository.getById(id);
	}

	@Override
	public Reservation create(Reservation entity) {
		entity.setBook(bookService.get(entity.getBook().getId()));
		entity.setUser(userService.get(entity.getUser().getId()));
		entity.setReservationDate(LocalDateTime.now());
		entity.setWasBorrowed(false);
		
		
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

	@Override
	public List<Reservation> getByBookId(UUID id) {
		return repository.getByBookId(id)
				.stream()
				.filter(this::wasNotBorrowed)
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Reservation> getFromListByUser(List<Reservation> reservations, UUID userId) {
		return reservations
				.stream()
				.filter(x->x.getUser().getId().equals(userId))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<Reservation> getCurrentByBookAndUser(UUID bookId, UUID userId) {
		return getByBookId(bookId)
				.stream()
				.filter(x->x.getUser().getId().equals(userId))
				.collect(Collectors.toList());
	}
	
	
	
	

}
