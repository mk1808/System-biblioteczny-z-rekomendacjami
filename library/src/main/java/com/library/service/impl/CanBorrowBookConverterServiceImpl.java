package com.library.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.library.dto.CanBorrowBookDto;
import com.library.dto.ChangeProposalDto;
import com.library.model.AppUser;
import com.library.model.Book;
import com.library.model.CanBorrowBook;
import com.library.model.ChangeProposal;
import com.library.service.BookConverterService;
import com.library.service.BookCopyConverterService;
import com.library.service.CanBorrowBookConverterService;
import com.library.service.UserConverterService;

@Service
public class CanBorrowBookConverterServiceImpl implements CanBorrowBookConverterService{

	private final BookConverterService bookConverter;
	private final BookCopyConverterService bookCopyConverter;
	private final UserConverterService userConverter;
	
	public CanBorrowBookConverterServiceImpl(BookConverterService bookConverter,BookCopyConverterService bookCopyConverter,UserConverterService userConverter) {
		this.bookConverter = bookConverter;
		this.bookCopyConverter = bookCopyConverter;
		this.userConverter = userConverter;
	}
	@Override
	public CanBorrowBookDto toDto(CanBorrowBook model) {
		CanBorrowBookDto dto = CanBorrowBookDto.builder()
				.book(bookConverter.toDto(model.getBook()))
				.bookCopy(bookCopyConverter.toDto(model.getBookCopy()))
				.user(userConverter.toDto(model.getUser()))
				.bookAvailabilityDto(model.getBookAvailabilityDto())
				.userAvailabilityDto(model.getUserAvailabilityDto())
				.canBorrow(model.getCanBorrow())
				.isReservedByUser(model.getIsReservedByUser())
				.reservationId(model.getReservationId())
				.build();
		return dto;
	}

	@Override
	public CanBorrowBook toModel(CanBorrowBookDto dto) {
		CanBorrowBook model = CanBorrowBook.builder()
				.book(bookConverter.toModel(dto.getBook()))
				.bookCopy(bookCopyConverter.toModel(dto.getBookCopy()))
				.user(userConverter.toModel(dto.getUser()))
				.bookAvailabilityDto(dto.getBookAvailabilityDto())
				.userAvailabilityDto(dto.getUserAvailabilityDto())
				.canBorrow(dto.getCanBorrow())
				.isReservedByUser(dto.getIsReservedByUser())
				.reservationId(dto.getReservationId())
				.build();
		return model;
	}

	@Override
	public List<CanBorrowBookDto> toDtoList(List<CanBorrowBook> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CanBorrowBook> toModelList(List<CanBorrowBookDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CanBorrowBookDto> toDtoPage(Page<CanBorrowBook> models) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<CanBorrowBook> toModelPage(Page<CanBorrowBookDto> dtos) {
		// TODO Auto-generated method stub
		return null;
	}

}
