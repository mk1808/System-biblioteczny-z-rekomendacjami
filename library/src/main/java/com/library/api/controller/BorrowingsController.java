package com.library.api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.BorrowingsResource;
import com.library.dto.BorrowingDto;
import com.library.response.Response;

@Controller
public class BorrowingsController implements BorrowingsResource{

	@Override
	public ResponseEntity<Response<List<BorrowingDto>>> getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<List<BorrowingDto>>> getPastByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<String>> prolong(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<String>> create(List<BorrowingDto> borrowings) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<String>> returnBorrowing(List<Long> bookCopiesIds) {
		// TODO Auto-generated method stub
		return null;
	}

}
