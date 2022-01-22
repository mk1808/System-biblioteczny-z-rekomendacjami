package com.library.service;

import java.util.List;
import java.util.UUID;

import com.library.model.Borrowing;

public interface BorrowingService extends RepositoryService<Borrowing> {

	List<Borrowing> getByUserId(UUID userId);

	void createMultiple(List<Borrowing> borrowings);

	void returnBorrowings(List<UUID> bookCopiesIds);

	List<Borrowing> getPastByUserId(UUID userId);

	void prolong(UUID id);

	List<Borrowing> getByBookCopyId(UUID bookCopyId);

}
