package com.library.service;

import java.util.List;
import java.util.UUID;

import com.library.dto.BorrowingDto;
import com.library.model.Borrowing;

public interface BorrowingService extends RepositoryService<Borrowing> {

	List<Borrowing> getByUserId(UUID userId);

	void createMultiple(List<BorrowingDto> borrowingsDtos);

	void returnBorrowings(List<UUID> bookCopiesIds);

	List<Borrowing> getPastByUserId(UUID userId);

	void prolong(UUID id);

}
