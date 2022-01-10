package com.library.service;

import java.util.List;

import com.library.dto.BorrowingDto;
import com.library.model.Borrowing;

public interface BorrowingService extends RepositoryService<Borrowing> {

	List<Borrowing> getByUserId(Long userId);

	void createMultiple(List<BorrowingDto> borrowingsDtos);

	void returnBorrowings(List<Long> bookCopiesIds);

	List<Borrowing> getPastByUserId(Long userId);

	void prolong(Long id);

}
