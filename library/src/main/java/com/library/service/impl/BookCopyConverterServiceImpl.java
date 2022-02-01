package com.library.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.AuthorDto;
import com.library.dto.BookCopyDto;
import com.library.dto.BookDto;
import com.library.model.Author;
import com.library.model.Book;
import com.library.model.BookCopy;
import com.library.service.BookConverterService;
import com.library.service.BookCopyConverterService;

@Service
public class BookCopyConverterServiceImpl implements BookCopyConverterService {

	private final BookConverterService bookConverter;
	
	@Autowired
	public BookCopyConverterServiceImpl(BookConverterService bookConverter) {
		super();
		this.bookConverter = bookConverter;
	}

	@Override
	public BookCopyDto toDto(BookCopy model) {
		if(model == null) return null;
		BookCopyDto dto = BookCopyDto.builder()
				.id(model.getId())
				.bookId(model.getBook().getId())
				.book(bookConverter.toDto(model.getBook()))
				.status(model.getStatus())
				.build();
		return dto;
	}

	@Override
	public BookCopy toModel(BookCopyDto dto) {
		BookCopy model = BookCopy.builder()
				.id(dto.getId())
				.book(Book.builder().id(dto.getBookId()).build())
				.status(dto.getStatus())
				.build();
		return model;
	}

	@Override
	public List<BookCopyDto> toDtoList(List<BookCopy> models) {
		return models.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public List<BookCopy> toModelList(List<BookCopyDto> dtos) {
		return dtos.stream().map(this::toModel).collect(Collectors.toList());
	}

	@Override
	public Page<BookCopyDto> toDtoPage(Page<BookCopy> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<BookCopy> toModelPage(Page<BookCopyDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
