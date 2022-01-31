package com.library.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.library.model.BookCopy;
import com.library.model.Borrowing;

@Service
public interface BorrowingService extends RepositoryService<Borrowing> {

	List<Borrowing> getByUserId(UUID userId);

	void createMultiple(List<Borrowing> borrowings);

	void returnBorrowings(List<UUID> bookCopiesIds);

	List<Borrowing> getPastByUserId(UUID userId);

	void prolong(UUID id);

	List<Borrowing> getByBookCopyId(UUID bookCopyId);
	
	List<Borrowing> getByBookCopiesIds(List<UUID> bookCopiesIds);

	List<Borrowing> getCurrentByBookCopies(List<BookCopy> bookCopies);

	List<Borrowing> getKeptTooLong(List<Borrowing> borrowings);
	
	

}
