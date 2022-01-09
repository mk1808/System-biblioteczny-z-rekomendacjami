package com.library.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;

import com.library.dto.BookDto;
import com.library.model.Book;
import com.library.service.BookConverterService;

public class BookConverterServiceImpl implements BookConverterService{

	@Override
	public BookDto toDto(Book model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book toModel(BookDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDto> toDtoList(List<Book> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> toModelList(List<BookDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<BookDto> toDtoPage(Page<Book> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Book> toModelPage(Page<BookDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}



}
