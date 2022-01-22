package com.library.service.impl;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.dto.BorrowingDto;
import com.library.model.BookCopy;
import com.library.model.Borrowing;
import com.library.repository.BorrowingRepository;
import com.library.service.BookService;
import com.library.service.BorrowingService;

@Service
public class BorrowingServiceImpl implements BorrowingService {
	private final BorrowingRepository repository;
	private final BookService bookService;
	private final Long BORROW_PERIOD=30L;
	
	@Autowired
	public BorrowingServiceImpl(BorrowingRepository repository, BookService bookService) {
		this.repository = repository;
		this.bookService = bookService;
	}
	@Override
	public Borrowing get(UUID id) {
		return repository.getById(id);
	}

	@Override
	public Borrowing create(Borrowing entity) {
		LocalDateTime now = LocalDateTime.now();
		entity.setBorrowDate(now);
		return repository.save(entity);
	}

	@Override
	public Borrowing update(Borrowing entity) {
		return repository.save(entity);
	}

	@Override
	public List<Borrowing> getByUserId(UUID userId) {
		
		return repository.getByUserId(userId)
				.stream()
				.filter(borrowing->!this.isReturned(borrowing))
				.collect(Collectors.toList());
	}

	@Override
	public void createMultiple(List<Borrowing> borrowings) {
		borrowings.stream().forEach(this::create);
		
	}
	
	@Override
	public List<Borrowing> getByBookCopyId(UUID bookCopyId) {
		return repository.getByBookCopyId(bookCopyId);
		
		
	}

	@Override
	public void returnBorrowings(List<UUID> bookCopiesIds) {
		List<Borrowing> borrowings = bookCopiesIds.stream()
				.map(this::getByBookCopyId)
				.flatMap(List::stream)
				.filter(borrowing->!this.isReturned(borrowing))
				.collect(Collectors.toList());
		LocalDateTime now = LocalDateTime.now();
		borrowings.stream().forEach(borrowing->borrowing.setReturnDate(now));
		borrowings.stream().forEach(this::update);
		
		
	}
	
	private Boolean isReturned(Borrowing borrowing) {
		return borrowing.getReturnDate()!=null;
	}

	@Override
	public List<Borrowing> getPastByUserId(UUID userId) {
		return repository.getByUserId(userId)
				.stream()
				.filter(borrowing->this.isReturned(borrowing))
				.collect(Collectors.toList());
	}

	@Override
	public void prolong(UUID id) {
		Borrowing borrowing = repository.getById(id);
		borrowing.setExpectedReturnDate(borrowing.getExpectedReturnDate().plusDays(BORROW_PERIOD));
		borrowing.setNumberOfProlongings(borrowing.getNumberOfProlongings()+1);
	}

}
