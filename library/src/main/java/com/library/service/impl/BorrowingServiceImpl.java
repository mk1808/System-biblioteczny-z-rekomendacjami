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
	//private final BookService bookService;
	private final Long BORROW_PERIOD=30L;
	
	@Autowired
	public BorrowingServiceImpl(BorrowingRepository repository) {//, BookService bookService
		this.repository = repository;
		//this.bookService = bookService;
	}
	@Override
	public Borrowing get(UUID id) {
		return repository.getById(id);
	}

	@Override
	public Borrowing create(Borrowing entity) {
		LocalDateTime now = LocalDateTime.now();
		entity.setBorrowDate(now);
		entity.setExpectedReturnDate(now.plusDays(BORROW_PERIOD));
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
	

	public List<Borrowing> getCurrentByBookCopyId(UUID bookCopyId) {
		return repository.getByBookCopyId(bookCopyId).stream().filter(borrowing->!this.isReturned(borrowing))
				.collect(Collectors.toList());
		
		
	}
	
	@Override
	public List<Borrowing> getCurrentByBookCopies(List<BookCopy> bookCopies) {
		return bookCopies.stream().map(BookCopy::getId)
				.map(this::getByBookCopyId)
				.flatMap(List::stream)
				.filter(borrowing->!this.isReturned(borrowing))
				.collect(Collectors.toList());
	}

	public List<Borrowing> getCurrentByBookCopiesIds(List<UUID> bookCopiesIds) {
		return bookCopiesIds.stream()
				.map(this::getByBookCopyId)
				.flatMap(List::stream)
				.filter(borrowing->!this.isReturned(borrowing))
				.collect(Collectors.toList());
	}

	@Override
	public void returnBorrowings(List<UUID> bookCopiesIds) {
		
		List<Borrowing> borrowings = getCurrentByBookCopiesIds(bookCopiesIds);
		LocalDateTime now = LocalDateTime.now();
		borrowings.stream().forEach(borrowing->borrowing.setReturnDate(now));
		borrowings.stream().forEach(this::update);
		
		System.out.println("returned");
	}
	
	private Boolean isReturned(Borrowing borrowing) {
		return borrowing.getReturnDate()!=null;
	}
	
	public Boolean isKeptTooLong(Borrowing borrowing) {
		if(borrowing.getExpectedReturnDate()==null)
		{
			return false;
		}
		int compared = LocalDateTime.now().compareTo(borrowing.getExpectedReturnDate());
				
		return compared<0;
	}
	
	@Override
	public List<Borrowing> getKeptTooLong(List<Borrowing> borrowings) {
		return borrowings
				.stream()
				.filter(this::isKeptTooLong)
				.collect(Collectors.toList());
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
	@Override
	public List<Borrowing> getByBookCopiesIds(List<UUID> bookCopiesIds) {
		return bookCopiesIds.stream().map(x->getByBookCopyId(x)).flatMap(List::stream).collect(Collectors.toList());
	}

}
