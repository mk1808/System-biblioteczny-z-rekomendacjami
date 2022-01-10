package com.library.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.BookCopyDto;
import com.library.model.BookCopy;
import com.library.service.BookCopyConverterService;

@Service
public class BookCopyConverterServiceImpl implements BookCopyConverterService {

	@Override
	public BookCopyDto toDto(BookCopy model) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookCopy toModel(BookCopyDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookCopyDto> toDtoList(List<BookCopy> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookCopy> toModelList(List<BookCopyDto> dtos) {
		// TODO Auto-generated method stub
		return null;
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
