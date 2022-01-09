package com.library.api.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.library.api.resource.BooksResource;
import com.library.dto.BookAvailabilityDto;
import com.library.dto.BookCopyDto;
import com.library.dto.BookDto;
import com.library.response.Response;

@Controller
public class BooksController implements BooksResource{

	@Override
	public ResponseEntity<Response<List<BookDto>>> getNewest(Long number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<List<BookDto>>> getPopular(Long number) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<Page<BookDto>>> getFiltered(Long pageNo, Long size) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<BookDto>> getById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<List<BookCopyDto>>> getBookCopiesByBookId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseEntity<Response<BookAvailabilityDto>> getAvailabilityByBookId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
