package com.library.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.library.dto.BorrowingDto;
import com.library.model.Borrowing;
import com.library.service.BorrowingService;

@Service
public class BorrowingServiceImpl implements BorrowingService {

	@Override
	public Borrowing get(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Borrowing create(Borrowing entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Borrowing update(Borrowing entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Borrowing> getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createMultiple(List<BorrowingDto> borrowingsDtos) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void returnBorrowings(List<Long> bookCopiesIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Borrowing> getPastByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void prolong(Long id) {
		// TODO Auto-generated method stub
		
	}

}
