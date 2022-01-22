package com.library.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.BorrowingsResource;
import com.library.dto.BookDto;
import com.library.dto.BorrowingDto;
import com.library.model.Book;
import com.library.model.Borrowing;
import com.library.model.Opinion;
import com.library.response.Response;
import com.library.service.BookConverterService;
import com.library.service.BorrowingConverterService;
import com.library.service.BorrowingService;

@Controller
public class BorrowingsController extends BaseController implements BorrowingsResource{
	
	private final BorrowingConverterService borrowingConverter;
	private final BorrowingService borrowingService;
	
	@Autowired
	public BorrowingsController(BorrowingConverterService borrowingConverter, BorrowingService borrowingService) {
		super();
		this.borrowingConverter = borrowingConverter;
		this.borrowingService = borrowingService;
	}

	@Override
	public ResponseEntity<Response<List<BorrowingDto>>> getByUserId(UUID userId) {
		Response<List<BorrowingDto>> response = createSuccessResponse(borrowingConverter.toDtoList(borrowingService.getByUserId(userId)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<List<BorrowingDto>>> getPastByUserId(UUID userId) {
		Response<List<BorrowingDto>> response = createSuccessResponse(borrowingConverter.toDtoList(borrowingService.getPastByUserId(userId)));
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<String>> prolong(UUID id) {
		borrowingService.prolong(id);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<String>> create(List<BorrowingDto> borrowingsDtos) {
		List<Borrowing> borrowings = borrowingConverter.toModelList(borrowingsDtos);
		borrowingService.createMultiple(borrowingsDtos);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<Response<String>> returnBorrowing(List<UUID> bookCopiesIds) {
		borrowingService.returnBorrowings(bookCopiesIds);
		Response<String> response = createSuccessResponse("");
		return ResponseEntity.ok(response);
	}

}
