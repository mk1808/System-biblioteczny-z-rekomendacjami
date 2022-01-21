package com.library.service.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.library.dto.BookCopyDto;
import com.library.dto.BorrowingDto;
import com.library.model.AppUser;
import com.library.model.BookCopy;
import com.library.model.Borrowing;
import com.library.service.BookConverterService;
import com.library.service.BookCopyConverterService;
import com.library.service.BorrowingConverterService;
import com.library.service.UserConverterService;

@Service
public class BorrowingConverterServiceImpl implements BorrowingConverterService{

private final BookCopyConverterService bookCopyConverter;
private final UserConverterService userConverter;
	
	@Autowired
	public BorrowingConverterServiceImpl(BookCopyConverterService bookCopyConverter, 
			UserConverterService userConverter) {
		this.bookCopyConverter = bookCopyConverter;
		this.userConverter = userConverter;
	}
	
	@Override
	public BorrowingDto toDto(Borrowing model) {
		BorrowingDto dto = BorrowingDto.builder()
				.id(model.getId())
				.bookCopy(bookCopyConverter.toDto(model.getBookCopy()))
				.bookCopyId(model.getBookCopy().getId())
				.user(userConverter.toDto(model.getUser()))
				.userId(model.getUser().getId())
				.borrowDate(model.getBorrowDate())
				.returnDate(model.getReturnDate())
				.expectedReturnDate(model.getExpectedReturnDate())
				.numberOfProlongings(model.getNumberOfProlongings())
				.build();
		return dto;
	}

	@Override
	public Borrowing toModel(BorrowingDto dto) {
		Borrowing model = Borrowing.builder()
				.id(dto.getId())
				.bookCopy(BookCopy.builder().id(dto.getBookCopyId()).build())
				.user(AppUser.builder().id(dto.getUserId()).build())
				.borrowDate(dto.getBorrowDate())
				.returnDate(dto.getReturnDate())
				.expectedReturnDate(dto.getExpectedReturnDate())
				.numberOfProlongings(dto.getNumberOfProlongings())
				.build();
		return model;
	}

	@Override
	public List<BorrowingDto> toDtoList(List<Borrowing> models) {
		return models.stream().map(this::toDto).collect(Collectors.toList());
	}

	@Override
	public List<Borrowing> toModelList(List<BorrowingDto> dtos) {
		return dtos.stream().map(this::toModel).collect(Collectors.toList());
	}

	@Override
	public Page<BorrowingDto> toDtoPage(Page<Borrowing> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Borrowing> toModelPage(Page<BorrowingDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
